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

import is.hi.teymi9.gefins.model.Ad;

/**
 * Created by Kristín María on 25.2.2018.
 */

public class AddAdFragment extends Fragment {

    private Spinner mSpinner;
    private Spinner mSpinner2;
    private Spinner mSpinner3;
    private Button mConfirm;
    private Button mBack;
    private RadioGroup mGiveorTake;
    private String mGiveOrTake;
    private EditText mNameOfAd;
    private EditText mDescription;
    private String mUsername;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mUsername = "user";

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

                System.out.println(mSpinner.getSelectedItem().toString());
                System.out.println(mSpinner2.getSelectedItem().toString());
                System.out.println(mSpinner3.getSelectedItem().toString());
                System.out.println(mGiveOrTake);
                System.out.println(mNameOfAd.getText().toString());
                System.out.println(mDescription.getText().toString());

                Ad mNewAd = new Ad(1, mGiveOrTake,
                        mNameOfAd.getText().toString(),
                        mSpinner.getSelectedItem().toString(),
                        mSpinner2.getSelectedItem().toString(),
                        mSpinner3.getSelectedItem().toString(),
                        mDescription.getText().toString(), mUsername);

                String message = AddAdActivity.getAdService().addAd(mNewAd, false);

                Toast.makeText(getActivity(),
                        message,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), UsersiteActivity.class);
                startActivity(intent);
            }
        });

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


