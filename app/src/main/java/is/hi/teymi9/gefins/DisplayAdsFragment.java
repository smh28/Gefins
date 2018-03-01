package is.hi.teymi9.gefins;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import is.hi.teymi9.gefins.model.Ad;

/**
 * author sandra
 */

public class DisplayAdsFragment extends Fragment {


    //TextView content;
    List<Ad> allAds;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_display_ads, container, false);   //var R.layout.fragment_display_ads

        ListView lv = (ListView) v.findViewById(R.id.listOfAds);      //var listOfAds

        allAds = DisplayAdsActivity.getAdService().getAllAds();
        System.out.println("allAds: " + allAds);

        int countAds = allAds.size();
        System.out.println("allAds stærð: " + countAds);

        String[] adName = new String[countAds];
        String[] adGiveOrTake = new String[countAds];
        String[] adDescription = new String[countAds];

        int i = 0;
        for(Ad a: allAds) {
            String name = a.getAdName();
            String giveOrTake = a.getGiveOrTake();
            String description = a.getAdDescription();
            adName[i] = name;
            adGiveOrTake[i] = giveOrTake;
            adDescription[i] = description;
            System.out.println("name augl: " + name);
            System.out.println("giveorTake: " + giveOrTake);
            System.out.println("description augl: " + description);
            System.out.println(i);
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
                String adPicked = "Þú valdir auglýsinguna " +
                        String.valueOf(lv.getItemAtPosition(position));
                Toast.makeText(getActivity(), adPicked, Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
