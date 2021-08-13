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
public class ItemDTO implements Serializable{
    private int itemID;
    private String itemName;
    private String color;
    private String category;
    private int quantity;
    private String itemDate;

    public ItemDTO(int itemID, String itemName, String color, String category, int quantity, String itemDate) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.color = color;
        this.category = category;
        this.quantity = quantity;
        this.itemDate = itemDate;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemDate() {
        return itemDate;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }
    
    
}
