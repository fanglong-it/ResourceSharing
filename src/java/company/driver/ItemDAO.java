/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.driver;

import company.connect.ConnectDB;
import java.sql.Connection;
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
public class ItemDAO implements java.io.Serializable {

    public List<ItemDTO> listFirstItem() throws NamingException, SQLException {
        List<ItemDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT TOP 5 ItemId, ItemName, Color, Category, Quantity, ItemDate\n"
                        + "FROM tblItem \n"
                        + "Where Quantity > 1";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {

                    int itemId = rs.getInt("ItemId");
                    String itemName = rs.getString("ItemName");
                    String color = rs.getString("Color");
                    String category = rs.getString("Category");
                    int quantity = rs.getInt("Quantity");
                    String itemDate = rs.getString("ItemDate");

                    ItemDTO item = new ItemDTO(itemId, itemName, color, category, quantity, itemDate);
                    list.add(item);
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

    public List<ItemDTO> listAllItem() throws NamingException, SQLException {
        List<ItemDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT ItemId, ItemName, Color, Category, Quantity, ItemDate\n"
                        + "FROM tblItem \n"
                        + "WHERE Quantity > 1";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {

                    int itemId = rs.getInt("ItemId");
                    String itemName = rs.getString("ItemName");
                    String color = rs.getString("Color");
                    String category = rs.getString("Category");
                    int quantity = rs.getInt("Quantity");
                    String itemDate = rs.getString("ItemDate");

                    ItemDTO item = new ItemDTO(itemId, itemName, color, category, quantity, itemDate);
                    list.add(item);
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

    public List<ItemDTO> listAllItemAdmin() throws NamingException, SQLException {
        List<ItemDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT ItemId, ItemName, Color, Category, Quantity, ItemDate\n"
                        + "FROM tblItem";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {

                    int itemId = rs.getInt("ItemId");
                    String itemName = rs.getString("ItemName");
                    String color = rs.getString("Color");
                    String category = rs.getString("Category");
                    int quantity = rs.getInt("Quantity");
                    String itemDate = rs.getString("ItemDate");

                    ItemDTO item = new ItemDTO(itemId, itemName, color, category, quantity, itemDate);
                    list.add(item);
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

    public List<ItemDTO> searchItem(String value) throws NamingException, SQLException {
        List<ItemDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT ItemId, ItemName, Color, Category, Quantity, ItemDate\n"
                        + "FROM tblItem\n"
                        + "WHERE ItemName like ? or Color like ? \n"
                        + "or Category like ? or ItemDate like ? \n"
                        + "and Quantity > 1";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + value + "%");
                pst.setString(2, "%" + value + "%");
                pst.setString(3, "%" + value + "%");
                pst.setString(4, "%" + value + "%");

                rs = pst.executeQuery();
                while (rs.next()) {

                    int itemId = rs.getInt("ItemId");
                    String itemName = rs.getString("ItemName");
                    String color = rs.getString("Color");
                    String category = rs.getString("Category");
                    int quantity = rs.getInt("Quantity");
                    String itemDate = rs.getString("ItemDate");

                    ItemDTO item = new ItemDTO(itemId, itemName, color, category, quantity, itemDate);
                    list.add(item);
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

    public int getOldQuantity(int ItemID) throws NamingException, SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "SELECT Quantity \n"
                        + "FROM tblItem\n"
                        + "WHERE ItemId = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, ItemID);
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

    public boolean updateItemQuantity(int ItemID, int Quantity) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "UPDATE tblItem\n"
                        + "SET Quantity = ?\n"
                        + "WHERE ItemId = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, Quantity);
                pst.setInt(2, ItemID);
                if(pst.executeUpdate() > 0){
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
