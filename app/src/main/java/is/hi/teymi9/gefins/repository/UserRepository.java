package is.hi.teymi9.gefins.repository;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import is.hi.teymi9.gefins.model.User;

/**
 * Created by Sandra on 14.2.2018.
 */


public class UserRepository extends User {

    private ArrayList<User> userList = new ArrayList<User>();
    private User newUser;

    public List<User> getAll(){

        User user = new User("olla", "Olof Frida Magnusdottir", "olla@hi.is", "1234567", "olla", 200, "Sturlugata 2", true);
        User user2 = new User("sandra", "Sandra Mar Huldudottir", "sandra@hi.is", "1234567", "sandra", 201, "Sturlugata 3", false);

        userList.add(user);
        userList.add(user2);
        if(newUser != null) {
            userList.add(newUser);
        }
        System.out.println("getAll() aðferð í UserRepository: " + userList);
        return userList;
    }

    public void addUser(String username, String fullName, String email, String phone,
                        String password,int zip, String address) {
        newUser = new User(username, fullName, email, phone, password, zip,address, false);
    }
}
