/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.driver;

import company.connect.ConnectDB;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author cunpl
 */
public class UserDAO implements Serializable {

    public UserDTO checkLogin(String userId, String password) throws NamingException, SQLException {
        UserDTO user = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT UserId,password,PhoneNumber,Name,address,DateCreate,Role,status\n"
                        + "FROM tblUser\n"
                        + "WHERE UserId = ? and password = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userId);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    user = new UserDTO(rs.getString("UserId"),
                            rs.getString("password"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Name"),
                            rs.getString("address"),
                            rs.getString("DateCreate"),
                            rs.getString("Role"),
                            rs.getString("status"));

                }

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return user;
    }

    public boolean checkDuplicateUserName(String userId) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT UserId,Password,PhoneNumber,Name,address,DateCreate,role,status\n"
                        + "FROM tblUser\n"
                        + "WHERE UserId = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return false;
    }

    public boolean createAccount(UserDTO user) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "INSERT into"
                        + " tblUser values(?,?,?,?,?,?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, user.getUserId());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getPhoneNumber());
                pst.setString(4, user.getFullName());
                pst.setString(5, user.getAddress());
                //get Current Date
                Long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                pst.setString(6, date.toString());
                pst.setString(7, user.getRole());
                pst.setString(8, user.getStatus());

                if (pst.executeUpdate() > 0) {
                    return true;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return false;
    }

    public boolean updateStatus(String userId) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "UPDATE tblUser \n"
                        + "SET status = 'Active' \n"
                        + "WHERE UserId = ? ;";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userId);
                if(pst.executeUpdate() > 0){
                    return true;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return false;
    }
}
