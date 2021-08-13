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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author cunpl
 */
public class RequestDAO implements Serializable {

    public List<RequestDTO> listFirstRequest() throws SQLException, NamingException {
        List<RequestDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT top 20 tblRequest.UserId, tblItem.ItemName, tblRequest.ItemId, tblRequest.DateRequest,\n"
                        + "tblRequest.DateAccept, tblRequest.Status, tblRequest.Quantity\n"
                        + "FROM tblItem\n"
                        + "INNER JOIN tblRequest ON tblItem.ItemId=tblRequest.ItemId\n"
                        + "ORDER BY tblRequest.DateRequest DESC";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserId");
                    int itemId = rs.getInt("ItemId");
                    String itemName = rs.getString("ItemName");
                    String dateRequest = rs.getString("DateRequest");
                    String dateAccept = rs.getString("DateAccept");
                    String status = rs.getString("Status");
                    int quantity = rs.getInt("Quantity");
                    RequestDTO requestDto = new RequestDTO(userID, itemId, itemName, dateRequest, dateAccept, status, quantity);
                    list.add(requestDto);
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
        return list;
    }

    public List<RequestDTO> listAllRequest() throws SQLException, NamingException {
        List<RequestDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT tblRequest.UserId, tblItem.ItemName, tblRequest.ItemId, tblRequest.DateRequest,\n"
                        + "tblRequest.DateAccept, tblRequest.Status, tblRequest.Quantity\n"
                        + "FROM tblItem\n"
                        + "INNER JOIN tblRequest ON tblItem.ItemId=tblRequest.ItemId";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserId");
                    int itemId = rs.getInt("ItemId");
                    String itemName = rs.getString("ItemName");
                    String dateRequest = rs.getString("DateRequest");
                    String dateAccept = rs.getString("DateAccept");
                    String status = rs.getString("Status");
                    int quantity = rs.getInt("Quantity");
                    RequestDTO requestDto = new RequestDTO(userID, itemId, itemName, dateRequest, dateAccept, status, quantity);
                    list.add(requestDto);
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
        return list;
    }

    public List<RequestDTO> searchRequest(String searchValue) throws SQLException, NamingException {
        List<RequestDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT tblRequest.UserId, tblItem.ItemName, tblRequest.ItemId, tblRequest.DateRequest,\n"
                        + "tblRequest.DateAccept, tblRequest.Status, tblRequest.Quantity\n"
                        + "FROM tblItem\n"
                        + "INNER JOIN tblRequest ON tblItem.ItemId=tblRequest.ItemId\n"
                        + "WHERE tblRequest.UserId like ? \n"
                        + "or tblItem.ItemName like ? \n"
                        + "or tblRequest.DateRequest like ? \n"
                        + "or tblRequest.Status like ? \n"
                        + "or tblRequest.DateAccept like ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                pst.setString(2, "%" + searchValue + "%");
                pst.setString(3, "%" + searchValue + "%");
                pst.setString(4, "%" + searchValue + "%");
                pst.setString(5, "%" + searchValue + "%");

                rs = pst.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserId");
                    int itemId = rs.getInt("ItemId");
                    String itemName = rs.getString("ItemName");
                    String dateRequest = rs.getString("DateRequest");
                    String dateAccept = rs.getString("DateAccept");
                    String status = rs.getString("Status");
                    int quantity = rs.getInt("Quantity");
                    RequestDTO requestDto = new RequestDTO(userID, itemId, itemName, dateRequest, dateAccept, status, quantity);
                    list.add(requestDto);
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
        return list;
    }

    public List<RequestDTO> listRequestByName(String name) throws SQLException, NamingException {
        List<RequestDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT tblRequest.UserId, tblItem.ItemName, tblRequest.ItemId, tblRequest.DateRequest,\n"
                        + "tblRequest.DateAccept, tblRequest.Status, tblRequest.Quantity\n"
                        + "FROM tblItem\n"
                        + "INNER JOIN tblRequest ON tblItem.ItemId=tblRequest.ItemId\n"
                        + "WHERE tblRequest.UserId like ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserId");
                    int itemId = rs.getInt("ItemId");
                    String itemName = rs.getString("ItemName");
                    String dateRequest = rs.getString("DateRequest");
                    String dateAccept = rs.getString("DateAccept");
                    String status = rs.getString("Status");
                    int quantity = rs.getInt("Quantity");
                    RequestDTO requestDto = new RequestDTO(userID, itemId, itemName, dateRequest, dateAccept, status, quantity);
                    list.add(requestDto);
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
        return list;
    }

    public boolean bookingRequest(RequestDTO request) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "INSERT into tblRequest values(?,?,?,null,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, request.getUserID());
                pst.setInt(2, request.getItemId());
                //get current
                long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                pst.setString(3, date.toString());

                pst.setString(4, "New");
                pst.setInt(5, request.getQuantity());
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

    public boolean checkDuplicateRequest(RequestDTO request) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT tblRequest.UserId, tblItem.ItemName, tblRequest.ItemId, tblRequest.DateRequest,\n"
                        + "tblRequest.DateAccept, tblRequest.Status, tblRequest.Quantity\n"
                        + "FROM tblItem\n"
                        + "INNER JOIN tblRequest ON tblItem.ItemId=tblRequest.ItemId\n"
                        + "WHERE tblRequest.UserId = ? \n"
                        + "and tblRequest.ItemId = ? \n"
                        + "and tblRequest.Status= ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, request.getUserID());
                pst.setInt(2, request.getItemId());
                pst.setString(3, "New");
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

    public int getRequestQuantity(RequestDTO request) throws SQLException, NamingException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT Quantity\n"
                        + "FROM tblRequest\n"
                        + "WHERE UserId = ? \n"
                        + "and ItemId = ? \n"
                        + "and Status= ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, request.getUserID());
                pst.setInt(2, request.getItemId());
                pst.setString(3, "New");
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("Quantity");
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
        return result;
    }

    public boolean updateQuantityRequest(RequestDTO request) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "UPDATE tblRequest\n"
                        + "SET Quantity = ? \n"
                        + "WHERE UserId = ? \n"
                        + "and ItemId = ? \n"
                        + "and Status= ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, request.getQuantity());
                pst.setString(2, request.getUserID());
                pst.setInt(3, request.getItemId());
                pst.setString(4, "New");
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

    public boolean updateStatusAndDateAccept(RequestDTO request) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "UPDATE tblRequest\n"
                        + "SET Status = ?, DateAccept = ? \n"
                        + "WHERE UserId = ? \n"
                        + "and ItemId = ? \n"
                        + "and Status = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, request.getStatus());
                //get Current Date
                long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                pst.setString(2, date.toString());

                pst.setString(3, request.getUserID());
                pst.setInt(4, request.getItemId());
                pst.setString(5, "New");
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

    public boolean updateStatus(RequestDTO request) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "UPDATE tblRequest\n"
                        + "SET Status = ? \n"
                        + "WHERE UserId = ? \n"
                        + "and ItemId = ? \n"
                        + "and Status = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, request.getStatus());
                pst.setString(2, request.getUserID());
                pst.setInt(3, request.getItemId());
                pst.setString(4, "New");
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

    public boolean deleteStatus(RequestDTO request) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "UPDATE tblRequest\n"
                        + "SET Status = ? \n"
                        + "WHERE UserId = ? \n"
                        + "and ItemId = ? \n"
                        + "and Status = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, request.getStatus());
                pst.setString(2, request.getUserID());
                pst.setInt(3, request.getItemId());
                pst.setString(4, "Accept");
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

    public boolean checkExistAcceptStatus(String UserId, int ItemId) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT UserId, ItemId,Status\n"
                        + "FROM tblRequest\n"
                        + "WHERE UserId = ? \n"
                        + "and ItemId = ? \n"
                        + "and Status = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, UserId);
                pst.setInt(2, ItemId);
                pst.setString(3, "Accept");
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

    public int getAcceptRequestQuantity(String UserId, int ItemId) throws SQLException, NamingException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT UserId, ItemId,Status,Quantity\n"
                        + "FROM tblRequest\n"
                        + "WHERE UserId = ? \n"
                        + "and ItemId = ? \n"
                        + "and Status = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, UserId);
                pst.setInt(2, ItemId);
                pst.setString(3, "Accept");
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("Quantity");
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
        return result;

    }

    public boolean deleteNewRequest(String UserId, int ItemId) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "DELETE tblRequest \n"
                        + "WHERE UserId = ? \n"
                        + "and ItemId = ? \n"
                        + "and Status = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, UserId);
                pst.setInt(2, ItemId);
                pst.setString(3, "New");
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

    public boolean updateAcceptedQuantity(String UserId, int ItemId, int Quantity) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "UPDATE tblRequest\n"
                        + "SET Quantity = ?, DateAccept = ? \n"
                        + "WHERE UserId = ? \n"
                        + "and ItemId = ? \n"
                        + "and Status = ? ";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, Quantity);
                long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                pst.setString(2, date.toString());
                pst.setString(3, UserId);
                pst.setInt(4, ItemId);
                pst.setString(5, "Accept");
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

}
