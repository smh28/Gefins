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
 * Created by Einar on 24.2.2018.
 */

public class RegisterFragment extends Fragment {
    private User mNewUser;

    private EditText mUsername;
    private EditText mPassword;
    private EditText mPasswordRepeat;
    private EditText mEmail;
    private EditText mFullName;
    private EditText mPhoneNumber;
    private EditText mZipcode;
    private EditText mAddress;
    private Button mRegisterButton;
    private String validate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                    String message = LoginActivity.getUserService().addUser(mNewUser, false);

                    Toast.makeText(getActivity(),
                            message,
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), UsersiteActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(),
                            validate,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }
}
