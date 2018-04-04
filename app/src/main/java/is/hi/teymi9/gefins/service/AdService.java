package is.hi.teymi9.gefins.service;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import java.util.UUID;

import is.hi.teymi9.gefins.DisplaySingleAdActivity;
import is.hi.teymi9.gefins.LoginActivity;
import is.hi.teymi9.gefins.R;
import is.hi.teymi9.gefins.SearchTypeActivity;
import is.hi.teymi9.gefins.UsersiteActivity;
import is.hi.teymi9.gefins.AddAdActivity;
import is.hi.teymi9.gefins.DisplayAdsActivity;
import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.Comment;
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

    //Tómur strengur
    String EMPTY_STRING = "";
    // tag til að auðkenna skilaboð í logger
    public static final String TAG = AdService.class.getSimpleName();
    AdRepository adRepository = new AdRepository();

    Ad currentAd = null;

    private List<Ad> adList;
    // DisplayAdsActivity sem AdService hefur samskipti við
    private Activity displayAdsActivity = null;
    // addAdActivity sem AdService hefur samskipti við
    private Activity addAdActivity = null;
    // SearchAdActivity sem AdService hefur samskipti við
    private Activity searchTypeActivity = null;
    // SingleAdActivity sem AdService hefur samskipti við
    private Activity singleAdActivity = null;
    //private String serverUrl = "http://192.168.1.2:8080";
    private String serverUrl = "https://gefins.herokuapp.com";
    // Gson hlutur fyrir JSON vinnslu
    Gson gson = new Gson();
    // okhttp3 client fyrir samskipti við bakenda
    OkHttpClient client;

    public AdService() {
        client = new OkHttpClient();
    }

    //Sækir allar auglýsingar sem bakendi hefur sent á framenda til vistunar
    public List<Ad> getAllAds() {
        adList = adRepository.getAdList();
        return adList;
    }


    /**
     * Sendir auglýsingu sem búa skal til á bakenda þar sem henni er bætt við
     * @param u Auglýsing sem bæta skal við
     * @return Skilaboð þess efnis hvort að tókst að bæta auglýsingu við
     */
    public String addAd(Ad u) {
        String method = "/createAd";
        Activity newActivity = getAddAdActivity();
        System.out.println("AdService í addAd: newActivity er " + newActivity);
        if(LoginActivity.getUserService().isNetworkAvailable(getAddAdActivity())) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(u));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(u));
            System.out.println("Ný auglýsing send á bakenda");
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    addAdActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    //alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    addAdActivity.runOnUiThread(new Runnable() {
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
                            addAdActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //if (jsonData.toString().compareTo("Create user failed. Please try again.") == 0) {
                                    //    Toast.makeText(addAdActivity, R.string.create_user_failed, Toast.LENGTH_LONG).show();
                                    //}
                                    //else {
                                        //setCurrentUser(u);
                                        //((AddAdActivity) addAdActivity).createUserWasSucessful();
                                        Toast.makeText(addAdActivity, R.string.create_ad_success, Toast.LENGTH_LONG).show();
                                    //}
                                }
                            });
                        }
                        else {
                            Toast.makeText(addAdActivity, R.string.create_ad_failed, Toast.LENGTH_LONG).show();
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
            Toast.makeText(addAdActivity, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
        return "";
    }

/* Gamla local fallið
    public String addAd(Ad u, boolean validate) {
        if(validate) {
            // ganga úr skugga um að user info sé valid og user sé ekki þegar til
        }
        adRepository.addAd(u);
        return "Skráning auglýsingar tókst!"; // eitthvað vesen að nálgast strings.xml héðan...
    }
*/

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
     * Nær í auglýsingar á bakenda sem tilheyra ákveðnum flokkum og birtir þær í viðmóti
     */
    public void getAdsByType(String yfirflokkur, String undirflokkur, Activity a) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Ad ad = new Ad(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, comments, EMPTY_STRING);
        System.out.println("Í upphafi getAdsByType er yfirflokkur (á að vera tómur strengur) " + ad.getAdType());
        System.out.println("Í upphafi getAdsByType er undirflokkur (á að vera tómur strengur) " + ad.getAdTypeOfType());
        System.out.println("Yfirflokkur er: " + yfirflokkur);
        System.out.println("Undirflokkur er: " + undirflokkur);
        if(yfirflokkur!="Allt"){
           ad.setAdType(yfirflokkur);
        }
        if(undirflokkur!="Allt"){
            ad.setAdTypeOfType(undirflokkur);
        }
        String method = "/getAdsByType";
        if(LoginActivity.getUserService().isNetworkAvailable(a)) {
            User currentUser = LoginActivity.getUserService().getCurrentUser();
            System.out.println("AdService í upphafi getAdsByType: currentUser er " + currentUser);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(ad));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(ad));
            System.out.println("Beiðni um adsByType send á bakenda");
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
                                    System.out.println("adList er að stærð: " + adList.size());
                                    for(Ad a: adList) {
                                        System.out.println(a.getAdName());
                                    }
                                    System.out.println("AdService í response er adList stærð: " + adList.size());
                                    if (adList.size() == 0) {
                                        Toast.makeText(a, R.string.no_ads_found, Toast.LENGTH_LONG).show();
                                    } else {
                                        User currentUser = LoginActivity.getUserService().getCurrentUser();
                                        System.out.println("AdService í lok getAdsByType: currentUser er " + currentUser);
                                        adRepository.setAdList(adList);
                                        //((UsersiteActivity) a).displayUserAds();
                                        ((SearchTypeActivity) a).displayAds();

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

    /**
     * Eyðir ad locally og sendir beiðni á bakenda um að eyða ad þar líka
     * @param ad auglýsing sem skal eyða
     * @param a activity sem kallar á að eyða auglýsingu
     */
    public void deleteAd(Ad ad, Activity a) {
        String method = "/deleteAd";
        if(LoginActivity.getUserService().isNetworkAvailable(a)) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(ad));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(ad));
            System.out.println("Beiðni um að eyða ad send á bakenda");
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
                                    if (jsonData.toString().compareTo("Ad deleted successfully") == 0) {
                                        Toast.makeText(a, R.string.ad_deleted_successfully, Toast.LENGTH_LONG).show();
                                        adRepository.deleteAd(ad);
                                        getAllAds();
                                        ((DisplaySingleAdActivity)a).adDeletedSuccessfully();
                                    }
                                    else {
                                        Toast.makeText(a, R.string.ad_not_deleted, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else {
                            a.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(a, R.string.ad_not_deleted, Toast.LENGTH_LONG).show();
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

    public Activity getAddAdActivity() {
        return addAdActivity;
    }

    public void setAddAdActivity(Activity addAdActivity) {
        this.addAdActivity = addAdActivity;
    }

    public Activity getSearchTypeActivity() {
        return searchTypeActivity;
    }

    public void setSearchTypeActivity(Activity searchTypeActivity) {
        this.searchTypeActivity = searchTypeActivity;
    }

    public Ad getCurrentAd() {
        return currentAd;
    }

    public void setCurrentAd(Ad currentAd) {
        this.currentAd = currentAd;
    }

    public Activity getSingleAdActivity(){
        return singleAdActivity;
    }

    public void setSingleAdActivity(Activity singleAdActivity){
        this.singleAdActivity = singleAdActivity;
    }

}

