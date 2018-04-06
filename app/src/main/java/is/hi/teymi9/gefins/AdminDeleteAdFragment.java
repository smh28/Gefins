package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
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
import is.hi.teymi9.gefins.service.AdService;

/**
 * Fragment fyrir viðmótið í AdminDeleteAdActivity og virknina þar.
 *
 * @author Kristín María
 * @version 1.0
 */

public class AdminDeleteAdFragment extends Fragment {

    //TextView content;
    List<Ad> allAds;
    //Tilbaka takki
    private Button mBack;
    //Takki til að eyða auglýsingum
    private Button mDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setUsersiteActivity(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_delete_ad, container, false);
        ListView listView = (ListView) v.findViewById(R.id.listOfAds2);
        mBack = (Button) v.findViewById(R.id.back_button);
        mDelete = (Button) v.findViewById(R.id.delete_ad_button2);

        //Ath - þarf að breyta í getAds - þarf activity en það kemur villa
        allAds = DisplayAdsActivity.getAdService().getAllAds();

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
            i++;
        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String> (
                getActivity(),
                android.R.layout.simple_list_item_multiple_choice,//var: android.R.layout.simple_list_item_1   /  R.layout.fragment_list_of_ads
                adName
        );

        listView.setAdapter(listViewAdapter);

        //Sendir admin tilbaka á admin síðu
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User currentUser = LoginActivity.getUserService().getCurrentUser();
                System.out.println("DisplayAdsFragment í back hnappi: currentUser er " + currentUser);
                Intent intent = new Intent(getActivity(), AdminActivity.class);
                startActivity(intent);
            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checked = listView.getCheckedItemPositions();
                int size = checked.size(); // number of name-value pairs in the array
                for (int i = 0; i < size; i++) {
                    int key = checked.keyAt(i);
                    boolean value = checked.get(key);
                    if (value)
                        System.out.println(key);
                        String tag = listView.getItemAtPosition(key).toString();
                        System.out.println(tag);
                        AdminActivity.getAdService().deleteAdByName(tag, getActivity());
                }
            }
        });
        return v;
    }

}
