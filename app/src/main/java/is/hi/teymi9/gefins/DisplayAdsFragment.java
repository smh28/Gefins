package is.hi.teymi9.gefins;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.User;
import is.hi.teymi9.gefins.service.UserService;

/**
 * Fragment fyrir viðmótið í DisplayAdsActivity og virknina þar.
 *
 * @author Sandra
 * @version 1.0
 */

public class DisplayAdsFragment extends Fragment {

    //TextView content;
    List<Ad> allAds;
    // Tilbaka takki
    private Button mBack;
    //Þjónusta fyrir notanda
    public static UserService userService = new UserService();
    //Þjónusta fyrir auglýsingar

    public static UserService getUserService() {
        return userService;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setDisplayAdsActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_display_ads, container, false);   //var R.layout.fragment_display_ads
        ListView lv = (ListView) v.findViewById(R.id.listOfAds);      //var listOfAds

        mBack = (Button) v.findViewById(R.id.displayAdTilbaka);
        allAds = DisplayAdsActivity.getAdService().getAllAds();

        User currentUser = LoginActivity.getUserService().getCurrentUser();
        System.out.println("DisplayAdsFragment í upphafi: currentUser er " + currentUser);

        //Finnur fjölda auglýsinga
        int countAds = allAds.size();
        System.out.println("allAds stærð (fjöldi auglýsinga): " + countAds);

        //Býr til tóm strengjafylki að sömu stærð og fjöldi auglýsinga
        String[] adName = new String[countAds];
        String[] adGiveOrTake = new String[countAds];
        String[] adType = new String[countAds];
        String[] adTypeOfType = new String[countAds];
        String[] adColor = new String[countAds];
        String[] adDescription = new String[countAds];
        String[] adLocation = new String[countAds];
        String[] adUsername = new String[countAds];
        String[] adId = new String[countAds];

        int i = 0;
        for(Ad a: allAds) {
            //Fylla inn í strengjafylkin
            adName[i] = a.getAdName();
            adGiveOrTake[i] = a.getGiveorTake();
            adType[i] = a.getAdType();
            adTypeOfType[i] = a.getAdTypeOfType();
            adColor[i] = a.getAdColor();
            adDescription[i] = a.getAdDescription();
            adLocation[i] = a.getAdLocation();
            adUsername[i] = a.getAdUsername();
            adId[i] = a.getId().toString();

            System.out.println("ítrun nr.: " + i);
            System.out.println("name augl: " + adName[i]);
            System.out.println("giveorTake: " + adGiveOrTake[i]);
            System.out.println("description augl: " + adDescription[i]);

            i++;
        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,   //var: android.R.layout.simple_list_item_1   /  R.layout.fragment_list_of_ads
                adName
        );

        lv.setAdapter(listViewAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DisplayAdsActivity.getAdService().setCurrentAd(allAds.get(position));
                Intent intent = new Intent(getActivity(), DisplaySingleAdActivity.class);
                intent.putExtra("name", adName[position]);
                intent.putExtra("giveOrTake", adGiveOrTake[position]);
                intent.putExtra("type", adType[position]);
                intent.putExtra("typeOfType", adTypeOfType[position]);
                intent.putExtra("color", adColor[position]);
                intent.putExtra("description", adDescription[position]);
                intent.putExtra("location", adLocation[position]);
                intent.putExtra("username", adUsername[position]);

                startActivity(intent);

            }
        });

        //Sendir notanda tilbaka á usersite síðuna ef hann er loggaður inn
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User currentUser = LoginActivity.getUserService().getCurrentUser();
                System.out.println("DisplayAdsFragment í back hnappi: currentUser er " + currentUser);
                if(currentUser != null) {
                    Intent intent = new Intent(getActivity(), UsersiteActivity.class);
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

