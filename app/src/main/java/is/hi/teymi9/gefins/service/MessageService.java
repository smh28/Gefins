package is.hi.teymi9.gefins.service;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import is.hi.teymi9.gefins.InboxActivity;
import is.hi.teymi9.gefins.R;
import is.hi.teymi9.gefins.UsersiteActivity;
import is.hi.teymi9.gefins.WriteMessageActivity;
import is.hi.teymi9.gefins.model.Message;
import is.hi.teymi9.gefins.model.User;
import is.hi.teymi9.gefins.repository.MessageRepository;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Service klasi fyrir Message.
 * Sér um að senda, sækja og sýna einkaskilaboð.
 *
 * @author Einar
 * @version 1.0
 */

public class MessageService implements Serializable {

    // tag til að auðkenna skilaboð í logger
    public static final String TAG = MessageService.class.getSimpleName();
    // Addressan á servernum sem tala skal við. Haft localhost á þróunarskeiði en
    // verður síðar meir skipt út fyrir Heroku þjóninn
    //private String serverUrl = "http://192.168.1.2:8080";
    private String serverUrl = "https://gefins.herokuapp.com";
    // WriteMessageActivity sem MessageService hefur samskipti við
    private Activity writeMessageActivity = null;
    // Gson hlutur fyrir JSON vinnslu
    Gson gson = new Gson();
    // okhttp3 client fyrir samskipti við bakenda
    OkHttpClient client;
    // repository til að geyma local skilaboð
    MessageRepository messageRep = new MessageRepository();

    public MessageService() {
         client = new OkHttpClient();
    }

    public List<Message> getMessages() {
        return messageRep.getMessageList();
    }
    public List<Message> getOutboxMessages() {
        return messageRep.getOutboxMessageList();
    }

    /**
     * Sendir skilaboð á bakenda
     * @param m Skilaboðin sem senda skal
     */
    public void sendMessage(Message m, Activity a) {
        String method = "/sendMessage";
        if(isNetworkAvailable(getWriteMessageActivity())) {
            RequestBody body = RequestBody.create(MediaType.parse(
                    "application/json; charset=utf-8"),
                    gson.toJson(m));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(m));
            System.out.println("Skilaboð send á bakenda");
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    a.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    //alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    a.runOnUiThread(new Runnable() {
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
                            a.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Boolean b = gson.fromJson(jsonData, Boolean.class);
                                    if (b == Boolean.FALSE) {
                                        Toast.makeText(a, R.string.send_message_failed, Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(a, R.string.send_message_successful, Toast.LENGTH_LONG).show();
                                        ((WriteMessageActivity)a).sendMessageSuccessful();
                                    }
                                }
                            });
                        }
                        else {
                            a.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(a, R.string.send_message_failed, Toast.LENGTH_LONG).show();
                                }
                            });
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
            Toast.makeText(a, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Sækir skilaboð til ákveðins notanda á bakenda
     * @param u notandi hvers skilaboð skal sækja
     */
    public void getUserMessages(User u, Activity a) {
        String method = "/getUserMessages";
        if(isNetworkAvailable(a)) {
            RequestBody body = RequestBody.create(MediaType.parse(
                    "application/json; charset=utf-8"),
                    gson.toJson(u));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(u));
            System.out.println("Beiðni send á bakenda");
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    a.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    //alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    a.runOnUiThread(new Runnable() {
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
                            a.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Type type = new TypeToken<List<Message>>() {}.getType();
                                    ArrayList<Message> messageList = gson.fromJson(jsonData, type);
                                    if (messageList.size() == 0) {
                                        Toast.makeText(a, R.string.no_messages_found, Toast.LENGTH_LONG).show();
                                    } else {
                                        messageRep.setMessageList(messageList);
                                        ((UsersiteActivity) a).displayInbox();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(a, R.string.create_user_failed, Toast.LENGTH_LONG).show();
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
            Toast.makeText(a, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Sækir skilaboð til ákveðins notanda á bakenda
     * @param u notandi hvers skilaboð skal sækja
     */
    public void getMySentMessages(User u, Activity a) {
        String method = "/getMySentMessages";
        if(isNetworkAvailable(a)) {
            RequestBody body = RequestBody.create(MediaType.parse(
                    "application/json; charset=utf-8"),
                    gson.toJson(u));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(u));
            System.out.println("Beiðni send á bakenda");
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    a.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    //alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    a.runOnUiThread(new Runnable() {
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
                            a.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Type type = new TypeToken<List<Message>>() {}.getType();
                                    ArrayList<Message> messageList = gson.fromJson(jsonData, type);
                                    if (messageList.size() == 0) {
                                        Toast.makeText(a, R.string.no_messages_found, Toast.LENGTH_LONG).show();
                                    } else {
                                        messageRep.setOutboxMessageList(messageList);
                                        ((InboxActivity) a).displayOutbox();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(a, R.string.create_user_failed, Toast.LENGTH_LONG).show();
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
            Toast.makeText(a, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
    }

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

    public Activity getWriteMessageActivity() {
        return writeMessageActivity;
    }

    public void setWriteMessageActivity(Activity writeMessageActivity) {
        this.writeMessageActivity = writeMessageActivity;
    }
}
