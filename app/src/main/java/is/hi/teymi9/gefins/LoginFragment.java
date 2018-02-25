package is.hi.teymi9.gefins;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

public class LoginFragment extends Fragment {

    private Button mNewUser;
    private Button mLogin;
    private EditText mUserName;
    private EditText mPsw;
    private String iName;
    private String iPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mNewUser = (Button) v.findViewById(R.id.nyr_notandi);
        mLogin = (Button) v.findViewById(R.id.skra_inn);
        mUserName = (EditText) v.findViewById(R.id.user);
        mPsw = (EditText) v.findViewById(R.id.psw);

        mLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                iName = mUserName.getText().toString();
                iPassword = mPsw.getText().toString();
                User u = LoginActivity.getUserService().login(iName, iPassword);
                if(u != null){
                    Intent intent = new Intent(getActivity(), UsersiteActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(),
                            R.string.innskr_villa,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mNewUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = RegisterActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });

        return v;
    }

}
