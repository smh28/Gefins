package is.hi.teymi9.gefins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

import is.hi.teymi9.gefins.model.User;
import is.hi.teymi9.gefins.service.UserService;

// test test

public class MainActivity extends AppCompatActivity {

    private Button mNewUser;
    private Button mLogin;
    private EditText mUserName;
    private EditText mPsw;
    private UserService userService= new UserService();
    private String iName;
    private String iPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewUser = (Button) findViewById(R.id.nyr_notandi);
        mLogin = (Button) findViewById(R.id.skra_inn);
        mUserName = (EditText) findViewById(R.id.user);
        mPsw = (EditText) findViewById(R.id.psw);

        mNewUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, NewUserActivity.class);
                    startActivity(intent);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                iName = mUserName.getText().toString();
                iPassword = mPsw.getText().toString();
                if(userService.isUser(iName, iPassword)){
                    Intent intent = new Intent(MainActivity.this, UsersiteActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,
                            R.string.innskr_villa,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}
