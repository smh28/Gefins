package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.Comment;

/**
 * Fragment fyrir viðmótið í SearchTypeActivity og virknina þar.
 *
 * @author Sandra
 * @version 1.0
 * @date
 */


public class SearchTypeFragment extends Fragment {

    //Spinner þar sem að valið er hvernig tengund hluturinn sem verið er að gefa/óska eftir er
    private Spinner mSpinner;
    //Spinner þar sem að valið er hvernig tengund af tegund hluturinn sem verið er að gefa/óska eftir er
    private Spinner mSpinner2;
    //Spinner þar sem að valið er hvernig litur er á þeim hlut sem verið er að gefa/óska eftir
    private Spinner mSpinner3;
    //Takki fyrir staðfestingu á nýrri auglýsingu
    private Button mConfirm;
    //Takki þar sem farið er tilbaka á notendasíðu
    private Button mBack;
    //RadioGroup sem heldur utan um takkana Gefins og Óska eftir
    private RadioGroup mGiveorTake;
    //Stengur sem segir til um hvort hlutur sé gefins eða óskað er eftir
    private String mGiveOrTake;
   //Nýr ArrayList<Comment>
    private ArrayList<Comment> CommentList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setSearchTypeActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_type, container, false);



        //Yfirflokkkur
        mSpinner = (Spinner) v.findViewById(R.id.spinner4);
        //Undirflokkur
        mSpinner2 = (Spinner) v.findViewById(R.id.spinner5);
        //Litur
        mSpinner3 = (Spinner) v.findViewById(R.id.spinner6);
        //Staðfesting
        mConfirm = (Button) v.findViewById(R.id.confirm);
        //Tilbaka
        mBack = (Button) v.findViewById(R.id.tilbaka);
        //Gefins eða óska eftir
        mGiveorTake = (RadioGroup) v.findViewById(R.id.radiogroup);
        //mGiveOrTake = "Gefins";


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.hlutir_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.searchHusgogn_array, android.R.layout.simple_spinner_item);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = mSpinner.getSelectedItem().toString();
                if (spinnerValue.equalsIgnoreCase("Húsgögn")) {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.searchHusgogn_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                } else if(spinnerValue.equalsIgnoreCase("Fatnaður")){
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.searchFatnadur_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                } else if(spinnerValue.equalsIgnoreCase("Matur")) {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.searchMatur_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                } else {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.searchAnnad_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.searchHusgogn_array, android.R.layout.simple_spinner_item);
                mSpinner2.setAdapter(adapter2);
            }
        });

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.searchLitir_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner3.setAdapter(adapter3);

        /**
         * Listener sem breytir strengnum mGiveOrTake í "Gefins" eða "Óska eftir" eftir því í hvorn
         * RadioButton er hakað við í RadioGroup
         */
        mGiveorTake.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_give:
                        mGiveOrTake = "Gefins";
                        break;
                    case R.id.radio_take:
                        mGiveOrTake = "Óska eftir";
                        break;
                }
            }
        });


        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Í SearchType Spinner = yfirflokkur: " + mSpinner.getSelectedItem().toString());
                System.out.println("Í SearchType Spinner2 = undirflokkur: " + mSpinner2.getSelectedItem().toString());
                System.out.println("Í SearchType Spinner3 = litur: " + mSpinner3.getSelectedItem().toString());
                System.out.println("Í SearchType mGiveOrTake = gefins eða óska eftir: " + mGiveOrTake);

                //Kallar á aðferðina getAdsByType í AdService sem sér um að sækja og birta réttar auglýsingar
                SearchTypeActivity.getAdService().getAdsByType(
                        mSpinner.getSelectedItem().toString(),
                        mSpinner2.getSelectedItem().toString(),
                        getActivity()
                );

            }
        });

        // takki sem sendir notanda til baka
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UsersiteActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }


}
