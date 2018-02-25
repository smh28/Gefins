package is.hi.teymi9.gefins.model;

import java.io.Serializable;

/**
 * Created by Sandra on 14.2.2018.
 */


public class User implements Serializable {
    private String username;
    private String fullName;

    private String email;
    private String phonenr;
    private String password;
    private int zip;
    private String address;
    private boolean hasadminauthority;


    public User(String username, String fullName, String email, String phonenr, String password, int zip, String address, boolean hasadminauthority) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phonenr = phonenr;
        this.password = password;
        this.zip = zip;
        this.address = address;
        this.hasadminauthority = hasadminauthority;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isHasadminauthority() {
        return hasadminauthority;
    }

    public void setHasadminauthority(boolean hasadminauthority) {
        this.hasadminauthority = hasadminauthority;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

