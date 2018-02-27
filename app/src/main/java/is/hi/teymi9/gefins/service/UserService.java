package is.hi.teymi9.gefins.service;

import android.content.res.Resources;

import java.io.Serializable;
import java.util.List;

import is.hi.teymi9.gefins.R;
import is.hi.teymi9.gefins.model.User;
import is.hi.teymi9.gefins.repository.UserRepository;

/**
 * Service klasi fyrir User.
 * Sér um þjónustu við user: finna, búa til, breyta og staðfestingar.
 *
 * @author Sandra
 * @version 1.0
 */

public class UserService implements Serializable {

    // Geymla fyrir alla user-a
    UserRepository userRepository = new UserRepository();
    // Núverandi innskráður notandi
    User currentUser = null;
    // Strengur sem segir til um hvort að notandi er gildur, og ef ekki þá hvað er vandamálið
    String isOk;
    // Listi af notendum
    private List<User> userList;

    /**
     * Nær í alla users frá Repository og skilar þeim
     * @return Listi af users
     */
    public List<User> getAllUsers(){
        userList = userRepository.getAll();
        System.out.println("getAllUsers userList: " + userList.toString());
        return userList;
    }

    /**
     * Skráir inn notanda
     * @param username Notendanafn notanda
     * @param password Lykilorð
     * @return User-inn sem skráður var inn
     */
    public User login(String username, String password) {
        userList = getAllUsers();
        for(User u: userList){
            if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                currentUser = u;
                return u;
            }
        }
        return null;
    }

    /**
     * Skráir út notanda
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Segir til um hvort notandi er til eða ekki
     * @param username Notendanafn
     * @param password Lykilorð
     * @return true ef notandi er til, annars false
     */
    public Boolean isUser(String username, String password){
        userList = getAllUsers();

        for(User u: userList){
            if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                System.out.println("UserService gefur skilaboðin true, þ.e. finnur user-inn í userList");
                return true;
            }
        }
        System.out.println("UserService gefur skilaboðin false, þ.e. finnur ekki user-inn í userList");
        return false;
    }

    /**
     * Bætir notanda við repository
     * @param u Notandi sem bæta skal við
     * @param validate Á að staðfesta notanda fyrst eða ekki
     * @return Skilaboð þess efnis hvort að vistun tókst eða ekki
     */
    public String addUser(User u, boolean validate) {
        if(validate) {
            // ganga úr skugga um að user info sé valid og user sé ekki þegar til
        }
        userRepository.addUser(u);
        return "Nýskráning tókst!"; // eitthvað vesen að nálgast strings.xml héðan...
    }

    /**
     * Staðfestir upplýsingar fyrir notanda, þ.e. segir til um hvort að gildin séu gild
     * @param username Notendanafn
     * @param email Tölvupóstfang
     * @param password Lykilorð
     * @param fullName Fullt nafn
     * @param phone Símanúmer
     * @return null ef allt var í lagi, annars strengur með skilaboðum um vandamál
     */
    public String validateRegister(String username, String email, String password, String fullName, String phone){
        // núllstilla isOk
        isOk = null;

        // athuga hvort notendanafnið sé löglegt
        if(username.isEmpty() | username.length() < 4) {
            isOk = "Notendanafn þarf að vera fleiri en 3 stafir";
            return isOk;
        }
        if(email.isEmpty() | !email.contains("@")) {
            isOk = "Netfang þarf að vera gilt netfang";
            return isOk;
        }
        if(password.isEmpty()| password.length() < 4) {
            isOk = "Lykilorð þarf að vera fleiri en 3 stafir";
            return isOk;
        }
        if(fullName.isEmpty()| fullName.length() < 4 ) {
            isOk = "Vinsamlegast fyllið inn full nafn";
            return isOk;
        }
        if(phone.isEmpty() | phone.length()<7){
            isOk = "Vinsamlegast fyllið inn gilt símanúmer";
            return isOk;
        }

        return isOk;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Uppfærir notanda þegar upplýsingum um hann hefur verið breytt
     * @param u Notandinn sem uppfæra skal
     */
    public void updateUser(User u) {
        userRepository.updateUser(u);
    }
}
