package is.hi.teymi9.gefins.service;

import java.util.List;

import is.hi.teymi9.gefins.model.User;
import is.hi.teymi9.gefins.repository.UserRepository;

/**
 * Created by Sandra on 14.2.2018.
 */


public class UserService {

    UserRepository allUsers = new UserRepository();

    private List<User> userList;

    public List<User> getAllUsers(){
        userList = allUsers.getAll();
        System.out.println("getAllUsers userList: " + userList);
        return userList;
    }


    public void addUser(String userName, String fullName, String password,
                        String phone, int zip, String email){
        allUsers.addUser(userName,fullName,email,phone,password,zip,"address");
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

    private String validation;

    public String validateUser(String userName, String fullName, String psw, int zip, String phone) {
        // Athuga hvort notendanafn sé nú þegar til
        // Athuga hvort lykilorðið sé nógu gott
        // Athuga hvort póstfang sé rétt
        // Athuga hvort símanúmer sé gilt
        // Athuga hvort fullt nafn sé rétt út fyllt

        return validation;
    }


}
