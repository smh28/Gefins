package is.hi.teymi9.gefins.service;

import android.app.Activity;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import is.hi.teymi9.gefins.LoginActivity;
import is.hi.teymi9.gefins.R;
import is.hi.teymi9.gefins.UsersiteActivity;
import is.hi.teymi9.gefins.DisplayAdsActivity;
import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.User;
import is.hi.teymi9.gefins.repository.AdRepository;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Kristín María on 26.2.2018.
 */

public class AdService {

    // tag til að auðkenna skilaboð í logger
    public static final String TAG = AdService.class.getSimpleName();
    AdRepository adRepository = new AdRepository();

    Ad currentAd = null;

    private List<Ad> adList;
    // DisplayAdsActivity sem UserService hefur samskipti við
    private Activity displayAdsActivity = null;

    //private String serverUrl = "http://192.168.1.3:8080";
    private String serverUrl = "https://gefins.herokuapp.com";
    // Gson hlutur fyrir JSON vinnslu
    Gson gson = new Gson();
    // okhttp3 client fyrir samskipti við bakenda
    OkHttpClient client;

    public AdService() {
        client = new OkHttpClient();
    }


    public List<Ad> getAllAds() {
        adList = adRepository.getAdList();
        return adList;
    }

    public String addAd(Ad u, boolean validate) {
        if(validate) {
            // ganga úr skugga um að user info sé valid og user sé ekki þegar til
        }
        adRepository.addAd(u);
        return "Skráning auglýsingar tókst!"; // eitthvað vesen að nálgast strings.xml héðan...
    }

    /**
     * Nær í auglýsingar á bakenda og birtir þær í viðmóti
     */
    public void getAds(Activity a) {
        String method = "/getAds";
        if(LoginActivity.getUserService().isNetworkAvailable(a)) {
            User currentUser = LoginActivity.getUserService().getCurrentUser();
            System.out.println("AdService í upphafi getAds: currentUser er " + currentUser);
            //RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(u));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    //.post(body)
                    .build();
            //System.out.println(gson.toJson(u));
            System.out.println("Beiðni um ads send á bakenda");
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
                                    Type type = new TypeToken<List<Ad>>() {}.getType();
                                    ArrayList<Ad> adList = gson.fromJson(jsonData, type);
                                    if (adList.size() == 0) {
                                        Toast.makeText(a, R.string.no_ads_found, Toast.LENGTH_LONG).show();
                                    } else {
                                        User currentUser = LoginActivity.getUserService().getCurrentUser();
                                        System.out.println("AdService í lok getAds: currentUser er " + currentUser);
                                        adRepository.setAdList(adList);
                                        ((UsersiteActivity) a).displayAds();

                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(a, R.string.display_ads_failed, Toast.LENGTH_LONG).show();
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
     * Nær í allar auglýsingar núverandi innskráðs notanda á bakenda og birtir þær í viðmóti
     */
    public void getUserAds(User u, Activity a) {
        String method = "/getUserAds";
        if(LoginActivity.getUserService().isNetworkAvailable(a)) {
            User currentUser = LoginActivity.getUserService().getCurrentUser();
            System.out.println("AdService í upphafi getUserAds: currentUser er " + currentUser);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(u));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(u));
            System.out.println("Beiðni um user ads send á bakenda");
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
                                    Type type = new TypeToken<List<Ad>>() {}.getType();
                                    ArrayList<Ad> adList = gson.fromJson(jsonData, type);
                                    if (adList.size() == 0) {
                                        Toast.makeText(a, R.string.no_ads_found, Toast.LENGTH_LONG).show();
                                    } else {
                                        User currentUser = LoginActivity.getUserService().getCurrentUser();
                                        System.out.println("AdService í lok getUserAds: currentUser er " + currentUser);
                                        adRepository.setAdList(adList);
                                        ((UsersiteActivity) a).displayUserAds();
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

    public Ad getCurrentAd() {
        return currentAd;
    }

    public void setCurrentAd(Ad currentUser) {
        this.currentAd = currentAd;
    }


}
