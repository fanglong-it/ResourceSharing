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
public class UserDTO implements Serializable{
    private String userId;
    private String password;
    private String phoneNumber;
    private String fullName;
    private String address;
    private String dateCreate;
    private String role;
    private String status;

   

    public UserDTO(String userId, String password, String phoneNumber, String fullName, String address, String dateCreate, String role, String status) {
        this.userId = userId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.address = address;
        this.dateCreate = dateCreate;
        this.role = role;
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
