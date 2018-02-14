package is.hi.teymi9.gefins.service;

import java.util.List;

import is.hi.teymi9.gefins.model.User;
import is.hi.teymi9.gefins.repository.UserRepository;

/**
 * Created by Sandra on 14.2.2018.
 */


public class UserService {

    UserRepository allUsers;

    private List<User> userList;

    public List<User> getAllUsers(){

        userList = allUsers.getAll();
        System.out.println("getAllUsers userList: " + userList);
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


}
