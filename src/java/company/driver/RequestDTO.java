/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.driver;

import java.io.Serializable;

/**
 *
 * @author cunpl
 */
public class RequestDTO implements Serializable {

    private String userID;
    private int itemId;
    private String itemName;
    private String dateRequest;
    private String dateAccept;
    private String status;
    private int quantity;

    public RequestDTO(String userID, int itemId, String itemName, String dateRequest, String dateAccept, String status) {
        this.userID = userID;
        this.itemId = itemId;
        this.itemName = itemName;
        this.dateRequest = dateRequest;
        this.dateAccept = dateAccept;
        this.status = status;
        
    }

    public RequestDTO(String userID, int itemId, String itemName, String dateRequest, String dateAccept, String status, int quantity) {
        this.userID = userID;
        this.itemId = itemId;
        this.itemName = itemName;
        this.dateRequest = dateRequest;
        this.dateAccept = dateAccept;
        this.status = status;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(String dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getDateAccept() {
        return dateAccept;
    }

    public void setDateAccept(String dateAccept) {
        this.dateAccept = dateAccept;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
