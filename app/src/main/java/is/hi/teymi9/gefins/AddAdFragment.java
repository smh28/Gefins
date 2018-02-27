package is.hi.teymi9.gefins;

import android.content.Intent;
import android.database.DefaultDatabaseErrorHandler;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.User;

/**
 * Created by Kristín María on 25.2.2018.
 */

public class AddAdFragment extends Fragment {

    private EditText mNameOfAd;
    private EditText mDescription;
    private String mGiveOrTake = "give";
    private Button mAddAd;
    private Spinner mSpinner;
    private Spinner mSpinner2;
    private Spinner mSpinner3;
    private String mUsername = "user";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_addad, container, false);

        mNameOfAd = (EditText) v.findViewById(R.id.nameofad2);
        mDescription = (EditText) v.findViewById(R.id.addescription2);
        mSpinner = (Spinner) v.findViewById(R.id.spinner);
        mSpinner2 = (Spinner) v.findViewById(R.id.spinner2);
        mSpinner3 = (Spinner) v.findViewById(R.id.spinner3);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.hlutir_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2;
        String firstChoice = mSpinner.getSelectedItem().toString().trim();

        if (firstChoice.equalsIgnoreCase("Húsgögn")) {
            adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.husgogn_array, android.R.layout.simple_spinner_item);
        } else if (firstChoice.equalsIgnoreCase("Fatnaður")) {
            adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.fatnadur_array, android.R.layout.simple_spinner_item);
        } else if (firstChoice.equalsIgnoreCase("Matur")) {
            adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.matur_array, android.R.layout.simple_spinner_item);
        } else {
            adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.annad_array, android.R.layout.simple_spinner_item);
        }

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.litir_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
        mSpinner2.setAdapter(adapter2);
        mSpinner3.setAdapter(adapter3);

        mAddAd = (Button) v.findViewById(R.id.addadbutton);
        mAddAd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
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

        return v;
    }
}


