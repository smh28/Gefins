package is.hi.teymi9.gefins.service;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;

import is.hi.teymi9.gefins.EditUserActivity;
import is.hi.teymi9.gefins.LoginActivity;
import is.hi.teymi9.gefins.RegisterActivity;
import is.hi.teymi9.gefins.model.Credentials;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
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

    // tag til að auðkenna skilaboð í logger
    public static final String TAG = UserService.class.getSimpleName();
    // Geymla fyrir alla user-a
    UserRepository userRepository = new UserRepository();
    // Núverandi innskráður notandi
    User currentUser = null;
    // Strengur sem segir til um hvort að notandi er gildur, og ef ekki þá hvað er vandamálið
    String isOk;
    // Listi af notendum
    private List<User> userList;
    // Addressan á servernum sem tala skal við. Haft localhost á þróunarskeiði en
    // verður síðar meir skipt út fyrir Heroku þjóninn
    //private String serverUrl = "http://192.168.1.3:8080";
    private String serverUrl = "https://gefins.herokuapp.com";
    // LoginActivity sem UserService hefur samskipti við
    private Activity loginActivity = null;
    // UsersiteActivity sem UserService hefur samskipti við
    private Activity usersiteActivity = null;
    // RegisterActivity sem UserService hefur samskipti við
    private Activity registerActivity = null;
    // EditUserActivity sem UserService hefur samskipti við
    private Activity editUserActivity = null;
    // Gson hlutur fyrir JSON vinnslu
    Gson gson = new Gson();
    // okhttp3 client fyrir samskipti við bakenda
    OkHttpClient client;

    public UserService() {
         client = new OkHttpClient();
    }

    /**
     * Nær í alla users frá Repository og skilar þeim
     * @return Listi af users
     */
    public List<User> getAllUsers(){
        String method = "/getUsers";
        return userList;
    }

    /**
     * Skráir inn notanda
     * @param username Notendanafn notanda
     * @param password Lykilorð
     * @return User-inn sem skráður var inn
     */
    public void login(String username, String password) {
        Credentials loginCredentials = new Credentials(username, password);
        String method = "/login";
        if(isNetworkAvailable(getLoginActivity())) {
            RequestBody body = RequestBody.create(MediaType.parse(
                    "application/json; charset=utf-8"),
                    gson.toJson(loginCredentials));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(loginCredentials));
            System.out.println("Login uplýsingar sendur á bakenda");
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    loginActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    //alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    loginActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //toggleRefresh();
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            //System.out.println(jsonData.toString());
                            //We are not on main thread
                            //Need to call this method and pass a new Runnable thread
                            //to be able to update the view.
                            loginActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    User u = gson.fromJson(jsonData, User.class);
                                    if (u.getUsername() == null) {
                                        Toast.makeText(loginActivity, R.string.login_failed, Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        setCurrentUser(u);
                                        if(u.isHasadminauthority()){
                                            ((LoginActivity) loginActivity).adminLogin();
                                        } else {
                                            ((LoginActivity) loginActivity).loginWasSucessful();
                                        }
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(loginActivity, R.string.login_failed, Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } /*catch (JSONException e) {
                        Log.e(TAG, "JSON caught: ", e);
                    }*/
                }
            });
        }
        else {
            Toast.makeText(registerActivity, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
    }


    // Gamla login fallið
    /*public User login(String username, String password) {
        userList = getAllUsers();
        for(User u: userList){
            if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                currentUser = u;
                return u;
            }
        }
        return null;
    }*/

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
     * Sendir notanda sem búa skal til á bakenda þar sem honum er bætt við ef hann er í lagi
     * @param u Notandi sem bæta skal við
     * @return Skilaboð þess efnis hvort að tókst að bæta notanda við
     */
    public String addUser(User u) {
        String method = "/createUser";
        if(isNetworkAvailable(getRegisterActivity())) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(u));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(u));
            System.out.println("Nýr user sendur á bakenda");
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    registerActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    //alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    registerActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //toggleRefresh();
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            //System.out.println(jsonData.toString());
                            //We are not on main thread
                            //Need to call this method and pass a new Runnable thread
                            //to be able to update the view.
                            registerActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (jsonData.toString().compareTo("Create user failed. Please try again.") == 0) {
                                        Toast.makeText(registerActivity, R.string.create_user_failed, Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        setCurrentUser(u);
                                        ((RegisterActivity) registerActivity).createUserWasSucessful();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(registerActivity, R.string.create_user_failed, Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } /*catch (JSONException e) {
                        Log.e(TAG, "JSON caught: ", e);
                    }*/
                }
            });
        }
        else {
            Toast.makeText(registerActivity, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
        return "";
    }
    // gamla local fallið
    /*public String addUser(User u, boolean validate) {
        if(validate) {
            // ganga úr skugga um að user info sé valid og user sé ekki þegar til
        }
        userRepository.addUser(u);
        return "Nýskráning tókst!"; // eitthvað vesen að nálgast strings.xml héðan...
    }*/

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
    /**
     * Sendir notanda sem búa skal til á bakenda þar sem honum er bætt við ef hann er í lagi
     * @param u Notandi sem bæta skal við
     * @return Skilaboð þess efnis hvort að tókst að bæta notanda við
     */
    public String updateUser(User u) {
        String method = "/updateUser";
        if(isNetworkAvailable(getEditUserActivity())) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(u));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(u));
            System.out.println("Uppfærður user sendur á bakenda");
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    editUserActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    //alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    editUserActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //toggleRefresh();
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            //System.out.println(jsonData.toString());
                            //We are not on main thread
                            //Need to call this method and pass a new Runnable thread
                            //to be able to update the view.
                            editUserActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (jsonData.toString().compareTo("Update user failed") == 0) {
                                        Toast.makeText(editUserActivity, R.string.update_user_failed, Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        ((EditUserActivity) editUserActivity).updateUserWasSuccessful();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(editUserActivity, R.string.create_user_failed, Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } /*catch (JSONException e) {
                        Log.e(TAG, "JSON caught: ", e);
                    }*/
                }
            });
        }
        else {
            Toast.makeText(editUserActivity, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
        return "";
    }

    // gamla updateUser fallið
    /*public void updateUser(User u) {
        userRepository.updateUser(u);
    }*/

    /**
     * Skilar upplýsingum um hvort nettenging sé til staðar.
     * Tekið úr Stormy eftir Sigurð Gauta
     * @return true ef nettenging er til staðar, annars false
     */
    public boolean isNetworkAvailable(Activity a) {
        ConnectivityManager manager = (ConnectivityManager) a.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo!= null && networkInfo.isConnected()) isAvailable = true;
        System.out.println("Network is available: " + isAvailable);
        return isAvailable;
    }

    public Activity getLoginActivity() {
        return loginActivity;
    }

    public void setLoginActivity(Activity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public Activity getUsersiteActivity() {
        return usersiteActivity;
    }

    public void setUsersiteActivity(Activity usersiteActivity) {
        this.usersiteActivity = usersiteActivity;
    }

    public Activity getRegisterActivity() {
        return registerActivity;
    }

    public void setRegisterActivity(Activity registerActivity) {
        this.registerActivity = registerActivity;
    }

    public Activity getEditUserActivity() {
        return editUserActivity;
    }

    public void setEditUserActivity(Activity editUserActivity) {
        this.editUserActivity = editUserActivity;
    }
}
