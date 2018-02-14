package is.hi.teymi9.gefins.repository;

import android.util.Log;

import java.util.List;

import is.hi.teymi9.gefins.model.User;

/**
 * Created by Sandra on 14.2.2018.
 */


public class UserRepository extends User {

    private List<User> userList;


    public List<User> getAll(){


        User user = new User("olla", "Olof Frida Magnusdottir", "olla@hi.is", "1234567", "olla", 200, "Sturlugata 2", true);

        User user2 = new User("sandra", "Sandra Mar Huldudottir", "sandra@hi.is", "1234567", "sandra", 201, "Sturlugata 3", false);

        userList.add(user);
        userList.add(user2);

        return userList;
    }
}
