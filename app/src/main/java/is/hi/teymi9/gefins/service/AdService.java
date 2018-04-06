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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import is.hi.teymi9.gefins.AdminActivity;
import is.hi.teymi9.gefins.AdminDeleteAdActivity;
import is.hi.teymi9.gefins.DisplaySingleAdActivity;
import is.hi.teymi9.gefins.EditAdActivity;
import is.hi.teymi9.gefins.EditUserActivity;
import is.hi.teymi9.gefins.LoginActivity;
import is.hi.teymi9.gefins.R;
import is.hi.teymi9.gefins.SearchTypeActivity;
import is.hi.teymi9.gefins.UsersiteActivity;
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
 * @author Kristín María
 * @date 26.2.2018
 * @version 1.0
 *
 *
 */

public class AdService {

    //Tómur strengur
    String EMPTY_STRING = "";
    // tag til að auðkenna skilaboð í logger
    public static final String TAG = AdService.class.getSimpleName();
    // repository fyrir auglýsingar
    AdRepository adRepository = new AdRepository();

    Ad currentAd = null;
    // listi af auglýsingum
    private List<Ad> adList;
    // DisplayAdsActivity sem AdService hefur samskipti við
    private Activity displayAdsActivity = null;
    // addAdActivity sem AdService hefur samskipti við
    private Activity addAdActivity = null;
    // SearchAdActivity sem AdService hefur samskipti við
    private Activity searchTypeActivity = null;
    // SingleAdActivity sem AdService hefur samskipti við
    private Activity singleAdActivity = null;
    // EditAdActivity sem AdService hefur samskipti við
    private Activity editAdActivity = null;
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
     * Eyðir auglýsingu með ákveðnu nafni
     * @param adName
     * @param act
     */
    public void deleteAdByName(String adName, Activity act) {
        System.out.println(adName);
        adList = adRepository.getAdList();
        for(Ad a: adList) {
            if(adName.equals(a.getAdName())) {
                System.out.println(a);
                deleteAd(a,act);
            }
        }
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

    /**
     * Nær í auglýsingar á bakenda og birtir þær í viðmóti
     * @param a activity
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
                                        if(currentUser.getUsername().equals("olla")) {
                                            ((AdminActivity) a).displayAds();
                                        } else {
                                            ((UsersiteActivity) a).displayAds();
                                        }
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
     * @param yfirflokkur String
     * @param undirflokkur String
     * @param a Activity
     */
    public void getAdsByType(String giveOrTake, String yfirflokkur, String undirflokkur, String litur, Activity a) {

        ArrayList<Comment> comments = new ArrayList<Comment>();
        Ad ad = new Ad("Gefins", EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, comments, EMPTY_STRING);

        System.out.println("GiveOrTake er: " + giveOrTake);
        System.out.println("Yfirflokkur er: " + yfirflokkur);
        System.out.println("Undirflokkur er: " + undirflokkur);
        System.out.println("Litur er: " + litur);

        //Setja inn öll leitarskilyrði inn í nýju, tómu auglýsinguna ad
        if(giveOrTake != null) {
            ad.setGiveorTake(giveOrTake);
        }
        if(!"Allt".equals(yfirflokkur)){
           ad.setAdType(yfirflokkur);
        }
        if(!"Allt".equals(undirflokkur)){
            ad.setAdTypeOfType(undirflokkur);
        }
        if(!"Allir".equals(litur)){
            ad.setAdColor(litur);
        }
        System.out.println("ad.giveOrTake er: " + ad.getGiveorTake());
        System.out.println("ad.type er: " + ad.getAdType());
        System.out.println("ad.typeOfType er: " + ad.getAdTypeOfType());
        System.out.println("ad.color er: " + ad.getAdColor());

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
     * @param u User
     * @param a Activity
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
     * Uppfærir auglýsingu þegar upplýsingum hefur verið breytt
     * Sendir auglýsingu sem á að uppfæra á bakenda þar sem henni er bætt við ef hún er í lagi
     * @param a Auglýsingin sem á að uppfæra
     * @return Skilaboð þess efnis hvort að tókst að uppfæra auglýsingu
     * */
    public String updateAd(Ad a) {
        String method = "/updateAd";
        if(isNetworkAvailable(getEditAdActivity())) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(a));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(a));
            System.out.println("Uppfærð auglýsing send á bakenda");
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    editAdActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    //alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    editAdActivity.runOnUiThread(new Runnable() {
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
                            editAdActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (jsonData.toString().compareTo("Update ad failed") == 0) {
                                        Toast.makeText(editAdActivity, R.string.update_ad_failed, Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(editAdActivity, R.string.update_ad_success, Toast.LENGTH_LONG).show();
                                        ((EditAdActivity) editAdActivity).updateAdWasSuccessful();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(editAdActivity, R.string.update_ad_failed, Toast.LENGTH_LONG).show();
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
            Toast.makeText(editAdActivity, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
        return "";
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
                                        User currentUser = LoginActivity.getUserService().getCurrentUser();
                                        if(currentUser.getUsername().equals("olla")) {
                                            ((AdminDeleteAdActivity)a).adDeletedSuccessfully();
                                        } else {
                                            ((DisplaySingleAdActivity)a).adDeletedSuccessfully();
                                        }
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

    public Activity getEditAdActivity() {
        return editAdActivity;
    }

    public void setEditAdActivity(Activity editAdActivity) {
        this.editAdActivity = editAdActivity;
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

