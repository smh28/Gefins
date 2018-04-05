package is.hi.teymi9.gefins.service;


import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import is.hi.teymi9.gefins.DisplayCommentActivity;
import is.hi.teymi9.gefins.DisplaySingleAdActivity;
import is.hi.teymi9.gefins.LoginActivity;
import is.hi.teymi9.gefins.R;
import is.hi.teymi9.gefins.UsersiteActivity;
import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.Comment;
import is.hi.teymi9.gefins.repository.CommentRepository;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Service klasi fyrir Comment.
 * Sér um þjónustu við comment: finna, búa til, breyta og staðfestingar.
 *
 * @author Ólöf Fríða
 * @version 1.0
 * @date March 2018
 */

public class CommentService implements Serializable {

    // Geymsla fyrir athugasemdirnar
    private CommentRepository commentRep = new CommentRepository();
    // tag til að auðkenna skilaboð í logger
    public static final String TAG = CommentService.class.getSimpleName();
    // Tengjast vefþóni
    private String serverUrl = "https://gefins.herokuapp.com";
    // Listi af athugasemdum
    private List<Comment> commentList = new ArrayList<Comment>();
    // Gson hlutur fyrir JSON vinnslu
    Gson gson = new Gson();
    // okhttp3 client fyrir samskipti við bakenda
    OkHttpClient client;
    // Activity
    private Activity addCommentActivity = null;
    // Activity
    private Activity displaySingleAdActivity = null;
    // Current comment
    private Comment currentComment = null;

    public CommentService() {
        client = new OkHttpClient();
    }

    /**
     * finnur athugasemd með viðeigandi id
     * @param id id
     * @return listi af athugasemdum
     */
    public Comment findComment(UUID id) {
        for(Comment c: commentList) {
            if (id == c.getId())
                return c;
        }
        return null;
    }

    /**
     * Sendir athugasemd á bakenda þar sem henni er bætt við
     * @param comment Athugasemdin sem bæta skal við
     * @return Skilaboð þess efnis hvort að tókst að bæta auglýsingu við
     */
    public String addComment(Comment comment) {
        String method = "/createComment";
        Activity newActivity = getAddCommentActivity();
        System.out.println("AdService í addAd: newActivity er " + newActivity);
        if(LoginActivity.getUserService().isNetworkAvailable(getAddCommentActivity())) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(comment));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(comment));
            System.out.println("Ný athugasemd send á bakenda");
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    addCommentActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    //alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    addCommentActivity.runOnUiThread(new Runnable() {
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
                            addCommentActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //if (jsonData.toString().compareTo("Create user failed. Please try again.") == 0) {
                                    //    Toast.makeText(addAdActivity, R.string.create_user_failed, Toast.LENGTH_LONG).show();
                                    //}
                                    //else {
                                    //setCurrentUser(u);
                                    //((AddAdActivity) addAdActivity).createUserWasSucessful();
                                    Toast.makeText(addCommentActivity, "Ný athugasemd hefur verið búin til", Toast.LENGTH_LONG).show();
                                    //}
                                }
                            });
                        }
                        else {
                            Toast.makeText(addCommentActivity, "Ekki tókst að búa til auglýsingu, vinsamlegast reynið aftur", Toast.LENGTH_LONG).show();
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
            Toast.makeText(addCommentActivity, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
        return "";
    }

    /**
     * Sækir allar athugasemdir
     * @return
     */
    public List<Comment> getAllComments() {
        commentList = commentRep.getCommentList();
        return commentList;
    }


    /**
     * Nær í athugasemdir sem tilheyra auglýsingunni sem verið er að skoða
     * @param ad auglýsing
     * @return listi af athugasemdum
     */
    public void getAdComments(Ad ad, Activity a) {
        String method = "/getAdComments";
        if(LoginActivity.getUserService().isNetworkAvailable(a)) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(ad));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(ad));
            System.out.println("Beiðni um ad comments send á bakenda");
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
                                    Type type = new TypeToken<List<Comment>>() {}.getType();
                                    ArrayList<Comment> commentList = gson.fromJson(jsonData, type);
                                    if (commentList.size() == 0) {
                                        Toast.makeText(a, R.string.no_ads_found, Toast.LENGTH_LONG).show();
                                    } else {
                                        commentRep.setCommentsList(commentList);
                                        ((DisplaySingleAdActivity) a).displayAdComments();
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
     * Bætir athugasemndinni við í repo
     * @param c Comment
     */
    public void addAd(Comment c) {
        commentRep.addComment(c);
    }

    /**
     * skilar activity
     * @return activity
     */
    public Activity getAddCommentActivity() {
        return addCommentActivity;
    }

    /**
     * nær í activity
     * @param addCommentActivity
     */
    public void setAddCommentActivity(Activity addCommentActivity) {
        this.addCommentActivity = addCommentActivity;
    }

    /**
     * Eyðir athugasemd locally og sendir beiðni á bakenda um að eyða ad þar líka
     * @param comment athugasemd sem skal eyða
     * @param a activity sem kallar á að eyða auglýsingu
     */
    public void deleteComment(Comment comment, Activity a) {
        String method = "/deleteComment";
        if(LoginActivity.getUserService().isNetworkAvailable(a)) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(comment));
            Request request = new Request.Builder()
                    .url(serverUrl + method)
                    .post(body)
                    .build();
            System.out.println(gson.toJson(comment));
            System.out.println("Beiðni um að eyða athugasemd send á bakenda");
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
                                    if (jsonData.toString().compareTo("Comment deleted successfully") == 0) {
                                        Toast.makeText(a, R.string.ad_deleted_successfully, Toast.LENGTH_LONG).show();
                                        commentRep.deleteComment(comment);
                                        getAllComments();
                                        ((DisplayCommentActivity)a).displayAdComments();
                                    }
                                    else {
                                        Toast.makeText(a, "Ekki tókst að eyða athugasemdinni", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else {
                            a.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(a, "Ekki tókst að eyða athugasemdinni", Toast.LENGTH_LONG).show();
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




}
