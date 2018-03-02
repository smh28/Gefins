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
 * Fragment fyrir viðmótið í LoginActivity og virknina þar.
 *
 * @author Einar
 * @version 1.0
 */

public class LoginFragment extends Fragment {

    // Takki fyrir nýskráningu
    private Button mNewUser;
    // Takki fyrir innskráningu
    private Button mLogin;
    // Textasvið fyrir notendanafn
    private EditText mUserName;
    // Textasvið fyrir lykilorð
    private EditText mPsw;
    // Geymir notendanafn þess sem skrá skal inn
    private String iName;
    // Geymir lykilorð þess sem srká skal inn
    private String iPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setLoginActivity(getActivity());
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
                LoginActivity.getUserService().login(iName, iPassword);
                /*User u = LoginActivity.getUserService().login(iName, iPassword);
                if(u != null){
                    Intent intent = new Intent(getActivity(), UsersiteActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(),
                            R.string.innskr_villa,
                            Toast.LENGTH_SHORT).show();
                }*/
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
