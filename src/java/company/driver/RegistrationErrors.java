/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.driver;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegistrationErrors implements Serializable{
    
    private String usernameErrs;
    private String passwordErrs;
    private String rePasswordErrs;
    private String phoneNumberErrs;
    private String nameErrs;
    private String addressErrs;
    private String DuplicateUsername;

    public RegistrationErrors() {
    }

    public RegistrationErrors(String usernameErrs, String passwordErrs, String rePasswordErrs,
            String phoneNumberErrs, String nameErrs, String addressErrs, String DuplicateUsername) {
        this.usernameErrs = usernameErrs;
        this.passwordErrs = passwordErrs;
        this.rePasswordErrs = rePasswordErrs;
        this.phoneNumberErrs = phoneNumberErrs;
        this.nameErrs = nameErrs;
        this.addressErrs = addressErrs;
        this.DuplicateUsername = DuplicateUsername;
    }

    public String getUsernameErrs() {
        return usernameErrs;
    }

    public void setUsernameErrs(String usernameErrs) {
        this.usernameErrs = usernameErrs;
    }

    public String getPasswordErrs() {
        return passwordErrs;
    }

    public void setPasswordErrs(String passwordErrs) {
        this.passwordErrs = passwordErrs;
    }

    public String getRePasswordErrs() {
        return rePasswordErrs;
    }

    public void setRePasswordErrs(String rePasswordErrs) {
        this.rePasswordErrs = rePasswordErrs;
    }

    public String getPhoneNumberErrs() {
        return phoneNumberErrs;
    }

    public void setPhoneNumberErrs(String phoneNumberErrs) {
        this.phoneNumberErrs = phoneNumberErrs;
    }

    public String getNameErrs() {
        return nameErrs;
    }

    public void setNameErrs(String nameErrs) {
        this.nameErrs = nameErrs;
    }

    public String getAddressErrs() {
        return addressErrs;
    }

    public void setAddressErrs(String addressErrs) {
        this.addressErrs = addressErrs;
    }

    public String getDuplicateUsername() {
        return DuplicateUsername;
    }

    public void setDuplicateUsername(String DuplicateUsername) {
        this.DuplicateUsername = DuplicateUsername;
    }
    
    
    
    
    
}
