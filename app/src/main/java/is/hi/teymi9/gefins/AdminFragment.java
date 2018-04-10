package is.hi.teymi9.gefins;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import is.hi.teymi9.gefins.model.User;

/**
 * Fragment fyrir viðmótið í AdminActivity og virknina þar.
 *
 * @author Ólöf Fríða og Kristín María
 * @version 1.0
 * @date March 2018
 */

public class AdminFragment extends Fragment {

    // Listi af notendum
    //List<User> users;

    // Takki fyrir útskráningu
    private Button mLogout;
    // Takk til að skoða allar auglýsingar
    private Button mSeeAllAds;
    // Takki til að eyða auglýsingu
    private Button mDeleteAd;
    // Takki til að eyða notanda
    private Button mDeleteUser;
    // Listi fyrir notendur
    //private ListView mUserList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setUsersiteActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adminsite, container, false);

        mLogout = (Button) v.findViewById(R.id.logout_button);

        mSeeAllAds = (Button) v.findViewById(R.id.see_all_ads_button);
        mDeleteAd = (Button) v.findViewById(R.id.delete_ad_button);
        mDeleteUser = (Button) v.findViewById(R.id.delete_user_button);

        mSeeAllAds.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DisplayAdsActivity.getAdService().getAds(getActivity());
            }
        });

        mDeleteAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminActivity.getAdService().getAds(getActivity());
            }
        });

        /**
         * OnClickListener á eyða takka þar sem AdminDeleteUserActivity opnast ef ýtt er á
         */
        mDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminActivity.getUserService().getUsers(getActivity());
            }
        });

        /**
         * OnClickListener á útskráningar takka þar sem dialog opnast og spyr hvort notandi sé
         * viss um að vilja skrá sig út áður en útskráning á sér stað
         */
        mLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Útskráning")
                        .setMessage("Ertu viss um að þú viljir skrá þig út?")
                        .setPositiveButton("Já", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                LoginActivity.getUserService().logout();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Nei", null)
                        .show();
            }
        });

        return v;
    }
}
