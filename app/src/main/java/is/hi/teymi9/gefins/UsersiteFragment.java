package is.hi.teymi9.gefins;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Fragment fyrir viðmótið í UsersiteActivity og virknina þar.
 *
 * @author Einar
 * @version 1.0
 */

public class UsersiteFragment extends Fragment {

    // Takki fyrir útskráningu
    private Button mLogout;
    // Takki fyrir leit að öllum auglýsingum
    private Button mSearch;
    // Takki fyrir leit að auglýsingum eftir flokkum
    private Button mSearchType;
    // Takki fyrir að bæta við auglýsingu
    private Button mAddAd;
    // Takki fyrir að uppfæra notendaupplýsingar
    private Button mEditUser;
    // Takki fyrir mínar auglýsingar
    private Button mMyAds;
    // Takki fyrir innhólf
    private Button mInbox;
    // Takki fyrir ný einkaskilaboð
    private Button mNewMessage;
    // Titiltextinn á síðunni
    private TextView mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setUsersiteActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usersite, container, false);

        mLogout = (Button) v.findViewById(R.id.logout_button);
        mSearch = (Button) v.findViewById(R.id.search_button);
        mSearchType = (Button) v.findViewById(R.id.searchType_button);
        mAddAd = (Button) v.findViewById(R.id.add_ad_button);
        mEditUser = (Button) v.findViewById(R.id.edit_user_button);
        mMyAds = (Button) v.findViewById(R.id.my_ads_button);
        mInbox = (Button) v.findViewById(R.id.inbox);
        mNewMessage = (Button) v.findViewById(R.id.new_message);
        mTitle = (TextView) v.findViewById(R.id.title);

        if(LoginActivity.getUserService().getCurrentUser() != null) {
            mTitle.setText("Velkomin(n) " + LoginActivity.getUserService().getCurrentUser().getUsername());
        }

        mSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DisplayAdsActivity.getAdService().getAds(getActivity());
            }
        });

        mSearchType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SearchTypeActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });

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

        mEditUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = EditUserActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });

        mAddAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddAdActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });

        mMyAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayAdsActivity.getAdService().getUserAds(LoginActivity.getUserService().getCurrentUser(), getActivity());

                // Fyrir callbackið:

            }
        });

        mNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WriteMessageActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });

        mInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteMessageActivity
                        .getMessageService()
                        .getUserMessages(LoginActivity
                                        .getUserService()
                                        .getCurrentUser(), getActivity());
            }
        });

        return v;
    }
}
