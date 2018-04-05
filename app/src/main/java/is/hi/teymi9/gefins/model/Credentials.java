package is.hi.teymi9.gefins.model;

/**
 *
 * @author Einar
 * @date February 2018
 * @version 1.0
 *
 * Credentials klasi til að senda login upplýsingar á þjón
 */

public class Credentials {
    // Notendanafn
    private String username;
    // Lykilorð
    private String password;

    /**
     * Smiður
     * @param username
     * @param password
     */
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
