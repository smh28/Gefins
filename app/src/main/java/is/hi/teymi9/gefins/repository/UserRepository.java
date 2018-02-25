package is.hi.teymi9.gefins.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import is.hi.teymi9.gefins.model.User;

/**
 * Created by Sandra on 14.2.2018.
 */


public class UserRepository extends User implements Serializable {

    private ArrayList<User> userList = new ArrayList<User>();


    public List<User> getAll(){

        if(userList.isEmpty()) {
            User user = new User("olla", "Olof Frida Magnusdottir", "olla@hi.is", "1234567", "olla", 200, "Sturlugata 2", true);
            User user2 = new User("sandra", "Sandra Mar Huldudottir", "sandra@hi.is", "1234567", "sandra", 201, "Sturlugata 3", false);
            userList.add(user);
            userList.add(user2);
        }

        System.out.println("getAll() aðferð í UserRepository: " + userList);
        return userList;
    }

    public void addUser(User u) {
        userList.add(u);
        System.out.println("Notanda bætt við repository:" + u);
    }

    public void updateUser(User u) {
        // save updated user to DB
    }

}
