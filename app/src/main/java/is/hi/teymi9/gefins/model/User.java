package is.hi.teymi9.gefins.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Módel klasi fyrir notanda (User)
 *
 * @author Sandra
 * @date
 * @version 1.0
 */

public class User implements Serializable {

    // unique auðkenni fyrir user
    private UUID id = UUID.randomUUID();
    // notendanafn
    private String username;
    // fullt nafn
    private String fullName;
    // tölvuóstfang
    private String email;
    // símanúmer
    private String phonenr;
    // lykilorð
    private String password;
    // póstnúmer
    private int zip;
    // heimilisfang
    private String address;
    // segir til um hvort notandi er stjórnandi (admin) eða ekki
    private boolean hasadminauthority;

    /**
     * Smiður með viðföngum
     * @param username notendanafn
     * @param fullName fullt nafn
     * @param email tölvupóstfang
     * @param phonenr símanúmer
     * @param password lykilorð
     * @param zip póstnúmer
     * @param address heimilisfang
     * @param hasadminauthority er notandi admin?
     */
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

    /**
     * Tómur smiður
     */
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

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static String toJson(User u) {

        return "";
    }
}

