package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.hi.teymi9.gefins.model.User;

/**
 * Fragment fyrir viðmótið í RegisterActivity og virknina þar.
 *
 * @author Einar
 * @version 1.0
 * @date
 */

public class RegisterFragment extends Fragment {

    // Nýr notandi sem búa skal til
    private User mNewUser;
    // Textasvið fyrir notandanafn
    private EditText mUsername;
    // Textasvið fyrir lykilorð
    private EditText mPassword;
    // Textasvið fyrir endurtekið lykilorð
    private EditText mPasswordRepeat;
    // Textasvið fyrir tölvupóstfang
    private EditText mEmail;
    // Textasvið fyrir fullt nafn
    private EditText mFullName;
    // Textasvið fyrir símanúmer
    private EditText mPhoneNumber;
    // Textasvið fyrir póstnúmer
    private EditText mZipcode;
    // Textasvið fyrir heimilisfang
    private EditText mAddress;
    // Takki til að nýskrá notanda
    private Button mRegisterButton;
    // Segir til um hvort notandaupplýsingar séu gildar eður ei
    private String validate;
    // Takk til að hætta við
    private Button mCancelButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setRegisterActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        mUsername = (EditText) v.findViewById(R.id.username);
        mPassword = (EditText) v.findViewById(R.id.password);
        mPasswordRepeat = (EditText) v.findViewById(R.id.password_repeat);
        mEmail = (EditText) v.findViewById(R.id.email_address);
        mFullName = (EditText) v.findViewById(R.id.full_name);
        mPhoneNumber = (EditText) v.findViewById(R.id.phonenumber);
        mZipcode = (EditText) v.findViewById(R.id.zipcode);
        mAddress = (EditText) v.findViewById(R.id.address);

        // Við viljum mögulega bæta við TextChangedListener á þetta til þess að geta komið með
        // live feedback um hvort inntak sé gilt.

        mRegisterButton = (Button) v.findViewById(R.id.register);
        mRegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                validate = LoginActivity.getUserService().validateRegister(mUsername.getText().toString(),
                        mEmail.getText().toString(),mPassword.getText().toString(),mFullName.getText().toString(),
                        mPhoneNumber.getText().toString());
                if(validate == null){
                    mNewUser = new User(
                            mUsername.getText().toString(),
                            mFullName.getText().toString(),
                            mEmail.getText().toString(),
                            mPhoneNumber.getText().toString(),
                            mPassword.getText().toString(),
                            Integer.parseInt(mZipcode.getText().toString()),
                            mAddress.getText().toString(),
                            false);

                    String message = LoginActivity.getUserService().addUser(mNewUser);

                    Toast.makeText(getActivity(),
                            message,
                            Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getActivity(),
                            validate,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        mCancelButton = (Button) v.findViewById(R.id.cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        return v;
    }



}
