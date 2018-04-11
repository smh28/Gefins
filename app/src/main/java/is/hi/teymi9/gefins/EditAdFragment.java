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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.Comment;

/**
 * Fragment fyrir viðmótið í EditAdActivity og virknina þar.
 *
 * @author Sandra
 * @version 1.0
 */

public class EditAdFragment extends Fragment {

    //Spinner þar sem að valið er hvernig tegund hluturinn sem verið er að gefa/óska eftir er (yfirflokkur)
    private Spinner mSpinner;
    //Strengur sem segir til um hvað sé valið í mSpinner (hvaða yfirflokkur sé valinn)
    private String mSpinnerChoice;
    //Spinner þar sem að valið er hvernig tegund af tegund hluturinn sem verið er að gefa/óska eftir er
    private Spinner mSpinner2;
    //Strengur sem segir til um hvað sé valið í mSpinner2 (hvaða undirflokkur sé valinn)
    private String mSpinner2Choice;
    //Spinner þar sem að valið er hvernig litur er á þeim hlut sem verið er að gefa/óska eftir
    private Spinner mSpinner3;
    //Strengur sem segir til um hvað sé valið í mSpinner3 (hvaða litur sé valinn)
    private String mSpinner3Choice;
    //Takki fyrir staðfestingu á nýrri auglýsingu
    private Button mUpdateAd;
    //Takki þar sem farið er tilbaka á notendasíðu
    private Button mBack;
    //RadioGroup sem heldur utan um takkana Gefins og Óska eftir
    private RadioGroup mGiveorTake;
    //RadioButton sem heldur utan um takkann Gefins
    private RadioButton mGive;
    //RadioButton sem heldur utan um takkann Óska eftir
    private RadioButton mTake;
    //Stengur sem segir til um hvort hlutur sé gefins eða óskað er eftir
    private String mGiveTake;
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
        DisplayAdsActivity.getAdService().setEditAdActivity(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_ad, container, false);

        mSpinner = (Spinner) v.findViewById(R.id.spinner4);
        mSpinner2 = (Spinner) v.findViewById(R.id.spinner5);
        mSpinner3 = (Spinner) v.findViewById(R.id.spinner6);
        mUpdateAd = (Button) v.findViewById(R.id.uppfaera_auglysingu);
        mBack = (Button) v.findViewById(R.id.tilbaka);
        mGiveorTake = (RadioGroup) v.findViewById(R.id.radiogroup);
        mGive = (RadioButton) v.findViewById(R.id.radio_give);
        mTake = (RadioButton) v.findViewById(R.id.radio_take);
        mNameOfAd = (EditText) v.findViewById(R.id.nafn2);
        mDescription = (EditText) v.findViewById(R.id.skrifalysingu);
        mUsername = LoginActivity.getUserService().getCurrentUser().getUsername();
        mGiveTake = EditAdActivity.getAdService().getCurrentAd().getGiveorTake();
        mLocation = (EditText) v.findViewById(R.id.postnumer2);

        //Innihald valdar auglýsingar (currentAd) sett á breyturnar (til birtingar í viðmóti)
        mNameOfAd.setText(EditAdActivity.getAdService().getCurrentAd().getAdName());
        mDescription.setText(EditAdActivity.getAdService().getCurrentAd().getAdDescription());
        mLocation.setText(EditAdActivity.getAdService().getCurrentAd().getAdLocation());
        mSpinnerChoice = EditAdActivity.getAdService().getCurrentAd().getAdType();
        mSpinner2Choice = EditAdActivity.getAdService().getCurrentAd().getAdTypeOfType();
        mSpinner3Choice = EditAdActivity.getAdService().getCurrentAd().getAdColor();


        if("Gefins".equals(mGiveTake)){
            System.out.println("giveOrTake er: Gefins");
            mGive.setChecked(true);
            mGiveTake = "Gefins";
        }
        if("Óska eftir".equals(mGiveTake)){
            System.out.println("giveOrTake er: Óska eftir");
            mTake.setChecked(true);
            mGiveTake = "Óska eftir";
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.hlutir_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
        //Setja þann yfirflokk sem er á auglýsingunni á spinnerinn
        if (mSpinnerChoice != null) {
            int spinnerPosition = adapter.getPosition(mSpinnerChoice);
            mSpinner.setSelection(spinnerPosition);
        }

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.husgogn_array, android.R.layout.simple_spinner_item);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = mSpinner.getSelectedItem().toString();
                if (spinnerValue.equalsIgnoreCase("Húsgögn")) {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.husgogn_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                    //Setja þann undirflokk sem er á auglýsingunni á spinnerinn
                    if (mSpinner2Choice != null) {
                        int spinnerPosition = adapter2.getPosition(mSpinner2Choice);
                        mSpinner2.setSelection(spinnerPosition);
                    }
                } else if(spinnerValue.equalsIgnoreCase("Fatnaður")){
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.fatnadur_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                    //Setja þann undirflokk sem er á auglýsingunni á spinnerinn
                    if (mSpinner2Choice != null) {
                        int spinnerPosition = adapter2.getPosition(mSpinner2Choice);
                        mSpinner2.setSelection(spinnerPosition);
                    }
                } else if(spinnerValue.equalsIgnoreCase("Matur")) {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.matur_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                    //Setja þann undirflokk sem er á auglýsingunni á spinnerinn
                    if (mSpinner2Choice != null) {
                        int spinnerPosition = adapter2.getPosition(mSpinner2Choice);
                        mSpinner2.setSelection(spinnerPosition);
                    }
                } else {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.annad_array, android.R.layout.simple_spinner_item);
                    mSpinner2.setAdapter(adapter2);
                    //Setja þann undirflokk sem er á auglýsingunni á spinnerinn
                    if (mSpinner2Choice != null) {
                        int spinnerPosition = adapter2.getPosition(mSpinner2Choice);
                        mSpinner2.setSelection(spinnerPosition);
                    }
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
        //Setja þann lit sem er á auglýsingunni á spinnerinn
        if (mSpinner3Choice != null) {
            int spinnerPosition = adapter3.getPosition(mSpinner3Choice);
            mSpinner3.setSelection(spinnerPosition);
        }


        /**
         * Breytir strengnum mGiveOrTake í "Gefins" eða "Óska eftir" eftir því í hvorn
         * RadioButton er hakað við í RadioGroup
         */
        mGiveorTake.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_give:
                        mGiveTake = "Gefins";
                        break;
                    case R.id.radio_take:
                        mGiveTake = "Óska eftir";
                        break;
                }
            }
        });

        /**
         * Uppfærir upplýsingar í auglýsingu á framenda
         * og sendir beiðni um að uppfæra einnig á bakenda
         */
        mUpdateAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(mSpinner.getSelectedItem().toString());
                System.out.println(mSpinner2.getSelectedItem().toString());
                System.out.println(mSpinner3.getSelectedItem().toString());
                System.out.println(mGiveTake);
                System.out.println(mNameOfAd.getText().toString());
                System.out.println(mDescription.getText().toString());
                System.out.println(mLocation.getText().toString());
                System.out.println(mUsername);

                EditAdActivity.getAdService().getCurrentAd().setGiveorTake(mGiveTake);
                EditAdActivity.getAdService().getCurrentAd().setAdType(mSpinner.getSelectedItem().toString());
                EditAdActivity.getAdService().getCurrentAd().setAdTypeOfType(mSpinner2.getSelectedItem().toString());
                EditAdActivity.getAdService().getCurrentAd().setAdColor(mSpinner3.getSelectedItem().toString());
                EditAdActivity.getAdService().getCurrentAd().setAdName(mNameOfAd.getText().toString());
                EditAdActivity.getAdService().getCurrentAd().setAdDescription(mDescription.getText().toString());
                EditAdActivity.getAdService().getCurrentAd().setAdLocation(mLocation.getText().toString());

                EditAdActivity.getAdService().updateAd(EditAdActivity.getAdService().getCurrentAd());
                //getActivity().onBackPressed();

            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DisplaySingleAdActivity.class);
                Ad a = DisplayAdsActivity.getAdService().getCurrentAd();
                intent.putExtra("name", a.getAdName());
                intent.putExtra("giveOrTake", a.getGiveorTake());
                intent.putExtra("type", a.getAdType());
                intent.putExtra("typeOfType", a.getAdTypeOfType());
                intent.putExtra("color", a.getAdColor());
                intent.putExtra("description", a.getAdDescription());
                intent.putExtra("location", a.getAdLocation());
                intent.putExtra("username", a.getAdUsername());
                startActivity(intent);
                getActivity().onBackPressed();
            }
        });

        return v;
    }




}
