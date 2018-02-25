package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.hi.teymi9.gefins.service.UserService;


/**
 *
 */

public class NewUserActivity extends AppCompatActivity {

    private Button mSkra;
    private EditText mUserName;
    private EditText mEmail;
    private EditText mPhoneNumber;
    private EditText mPassword;
    private EditText mZip;
    private EditText mFullName;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String userName;
    private int zip;
    private UserService userService = new UserService();
    private String val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        mSkra = (Button) findViewById(R.id.skra);
        mUserName = (EditText) findViewById(R.id.userName);
        mEmail = (EditText) findViewById(R.id.email);
        mPhoneNumber = (EditText) findViewById(R.id.phone);
        mZip = (EditText) findViewById(R.id.zip);
        mPassword = (EditText) findViewById(R.id.password);
        mFullName = (EditText) findViewById(R.id.fullName);

        mSkra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userName = mUserName.getText().toString();
                password = mPassword.getText().toString();
                phone = mPhoneNumber.getText().toString();
                fullName = mFullName.getText().toString();
                zip = Integer.parseInt(mZip.getText().toString());

                // validate
                val = userService.validateUser(userName,fullName,password,zip,phone);

                // ef val er null þá búum við til notanda annars köstum við villu
                if(val == null){
                    userService.addUser(userName,fullName,password,phone,zip,email);
                    Intent intent = new Intent(NewUserActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(NewUserActivity.this,
                            val,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
