package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Fragment fyrir viðmótið í DisplaySingleAdActivity og virknina þar.
 *
 * @author Sandra
 * @version 1.0
 */

public class DisplaySingleAdFragment extends Fragment {

    // Tilbaka takki
    private Button mBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        Bundle bundle = getActivity().getIntent().getExtras();

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

        //Sendir notanda tilbaka á usersite síðuna
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DisplayAdsActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
