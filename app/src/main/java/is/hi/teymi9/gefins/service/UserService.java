package is.hi.teymi9.gefins.service;

import android.content.res.Resources;

import java.io.Serializable;
import java.util.List;

import is.hi.teymi9.gefins.R;
import is.hi.teymi9.gefins.model.User;
import is.hi.teymi9.gefins.repository.UserRepository;

/**
 * Created by Sandra on 14.2.2018.
 */


public class UserService implements Serializable {

    UserRepository userRepository = new UserRepository();

    private List<User> userList;

    public List<User> getAllUsers(){
        userList = userRepository.getAll();
        System.out.println("getAllUsers userList: " + userList.toString());
        return userList;
    }


    public Boolean isUser(String userName, String passw){
        userList = getAllUsers();

        for(User u: userList){
            if (userName.equals(u.getUsername()) && passw.equals(u.getPassword())) {
                System.out.println("UserService gefur skilaboðin true, þ.e. finnur user-inn í userList");
                return true;
            }
        }
        System.out.println("UserService gefur skilaboðin false, þ.e. finnur ekki user-inn í userList");
        return false;
    }

    public String addUser(User u, boolean validate) {
        if(validate) {
            // ganga úr skugga um að user info sé valid og user sé ekki þegar til
        }
        userRepository.addUser(u);
        return "Nýskráning tókst!"; // eitthvað vesen að nálgast strings.xml héðan...
    }

}
