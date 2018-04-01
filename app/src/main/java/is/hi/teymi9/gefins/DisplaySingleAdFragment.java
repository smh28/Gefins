package is.hi.teymi9.gefins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import is.hi.teymi9.gefins.model.User;
import is.hi.teymi9.gefins.service.UserService;

/**
 * Fragment fyrir viðmótið í DisplaySingleAdActivity og virknina þar.
 *
 * @author Sandra
 * @version 1.0
 */

public class DisplaySingleAdFragment extends Fragment {

    // Tilbaka takki
    private Button mBack;
    // Takki sem leyfir notanda að skoða ummæli
    private Button mComment;

    //Þjónusta fyrir notanda
    public static UserService userService = new UserService();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setDisplaySingleAdActivity(getActivity());
    }

    public static UserService getUserService() {
        return userService;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        User currentUser = LoginActivity.getUserService().getCurrentUser();
        System.out.println("DisplaySingleAdFragment í upphafi: currentUser er " + currentUser);

        View v = inflater.inflate(R.layout.fragment_display_single_ad,
                container, false);
        TextView tv1 = (TextView)v.findViewById((R.id.adName));
        TextView tv2 = (TextView)v.findViewById((R.id.adGiveOrTake));
        TextView tv3 = (TextView)v.findViewById((R.id.adType));
        TextView tv4 = (TextView)v.findViewById((R.id.adTypeOfType));
        TextView tv5 = (TextView)v.findViewById((R.id.adColor));
        TextView tv6 = (TextView)v.findViewById((R.id.adDescription));
        TextView tv7 = (TextView)v.findViewById((R.id.adLocation));
        TextView tv8 = (TextView)v.findViewById((R.id.adUsername));

        mBack = (Button) v.findViewById(R.id.singleAdTilbaka);
        mComment = (Button) v.findViewById(R.id.comments);

        currentUser = LoginActivity.getUserService().getCurrentUser();
        System.out.println("DisplaySingleAdFragment á undan bundle: currentUser er " + currentUser);

        Bundle bundle = getActivity().getIntent().getExtras();

        currentUser = LoginActivity.getUserService().getCurrentUser();
        System.out.println("DisplaySingleAdFragment á eftir bundle: currentUser er " + currentUser);


        if(bundle != null) {
            String adName = bundle.getString("name");
            String adGiveOrTake = bundle.getString("giveOrTake");
            String adType = bundle.getString("type");
            String adTypeOfType = bundle.getString("typeOfType");
            String adColor = bundle.getString("color");
            String adDescription = bundle.getString("description");
            String adLocation = bundle.getString("location");
            String adUsername = bundle.getString("username");
            tv1.setText(adName);
            tv2.setText("Gefins eða óskað eftir: " + adGiveOrTake);
            tv3.setText("Flokkur: " + adType);
            tv4.setText("Undirflokkur: " + adTypeOfType);
            tv5.setText("Litur: " + adColor);
            tv6.setText("Lýsing: " + adDescription);
            tv7.setText("Staðsetning: " + adLocation);
            tv8.setText("Höfundur auglýsingar: " + adUsername);
        }
        currentUser = LoginActivity.getUserService().getCurrentUser();
        System.out.println("DisplaySingleAdFragment á eftir bundle og if setningu: currentUser er " + currentUser);


        // Fer yfir í athugasemdir
        mComment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DisplayCommentActivity.commentService.getAdComments(DisplayAdsActivity.adService.getCurrentAd(), getActivity());
            }
        });

        currentUser = LoginActivity.getUserService().getCurrentUser();
        System.out.println("DisplaySingleAdFragment rétt á undan mBack aðferð: currentUser er " + currentUser);
        //Sendir notanda tilbaka á lista yfir auglýsingar síðuna ef hann er loggaður inn annars á login síðuna
        mBack.setOnClickListener(new View.OnClickListener(){
           User currentUser = LoginActivity.getUserService().getCurrentUser();

            @Override
            public void onClick(View v) {
                currentUser = userService.getCurrentUser();
                System.out.println("DisplaySingleAdFragment ofarlega inni í mBack hnappnum: currentUser er " + currentUser);
                if(currentUser != null) {
                    Intent intent = new Intent(getActivity(), DisplayAdsActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        return v;
    }
}
