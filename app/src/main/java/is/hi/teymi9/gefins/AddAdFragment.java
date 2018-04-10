package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.*;

/**
 * Fragment fyrir viðmótið í AddAdActivity og virknina þar.
 *
 * @author Kristín María
 * @version 1.0
 * @date
 */

public class AddAdFragment extends Fragment {

    //Spinner þar sem að valið er hvernig tegund hluturinn sem verið er að gefa/óska eftir er
    private Spinner mSpinner;
    //Spinner þar sem að valið er hvernig tegund af tegund hluturinn sem verið er að gefa/óska eftir er
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
    //Textasvið fyrir nafn á auglýsingu
    private EditText mNameOfAd;
    //Textasvið fyrir lýsingu á auglýsingu
    private EditText mDescription;
    //Strengur sem inniheldur notendanafn
    private String mUsername;
    //Staðsetning hlutar í póstnúmeri
    private EditText mLocation;
    //Nýr ArrayList<Comment>
    private ArrayList<Comment> CommentList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AddAdActivity.getAdService().setAddAdActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_ad, container, false);

        mSpinner = (Spinner) v.findViewById(R.id.spinner4);
        mSpinner2 = (Spinner) v.findViewById(R.id.spinner5);
        mSpinner3 = (Spinner) v.findViewById(R.id.spinner6);
        mConfirm = (Button) v.findViewById(R.id.confirm);
        mBack = (Button) v.findViewById(R.id.tilbaka);
        mGiveorTake = (RadioGroup) v.findViewById(R.id.radiogroup);
        mNameOfAd = (EditText) v.findViewById(R.id.nafn2);
        mDescription = (EditText) v.findViewById(R.id.skrifalysingu);
        mUsername = LoginActivity.getUserService().getCurrentUser().getUsername();
        mGiveOrTake = "Gefins";
        mLocation = (EditText) v.findViewById(R.id.postnumer2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.hlutir_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.husgogn_array, android.R.layout.simple_spinner_item);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = mSpinner.getSelectedItem().toString();
                if (spinnerValue.equalsIgnoreCase("Húsgögn")) {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.husgogn_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                } else if(spinnerValue.equalsIgnoreCase("Fatnaður")){
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.fatnadur_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                } else if(spinnerValue.equalsIgnoreCase("Matur")) {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.matur_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                } else {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.annad_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.husgogn_array, android.R.layout.simple_spinner_item);
                mSpinner2.setAdapter(adapter2);
            }
        });

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.litir_array, android.R.layout.simple_spinner_item);
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

        // Listenner sem staðfestir að búa skal nýja athugasemd
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(mSpinner.getSelectedItem().toString());
                System.out.println(mSpinner2.getSelectedItem().toString());
                System.out.println(mSpinner3.getSelectedItem().toString());
                System.out.println(mGiveOrTake);
                System.out.println(mNameOfAd.getText().toString());
                System.out.println(mDescription.getText().toString());
                System.out.println(mUsername);

                Ad mNewAd = new Ad(mGiveOrTake,
                        mNameOfAd.getText().toString(),
                        mSpinner.getSelectedItem().toString(),
                        mSpinner2.getSelectedItem().toString(),
                        mSpinner3.getSelectedItem().toString(),
                        mDescription.getText().toString(), mUsername, CommentList, mLocation.getText().toString());

                String message = AddAdActivity.getAdService().addAd(mNewAd);

                Toast.makeText(getActivity(),
                        message,
                        Toast.LENGTH_SHORT).show();

                getActivity().onBackPressed();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), UsersiteActivity.class);
                //startActivity(intent);
                getActivity().onBackPressed();
            }
        });

        return v;
    }
}


