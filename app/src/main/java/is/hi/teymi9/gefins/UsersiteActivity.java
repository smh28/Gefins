package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sandra on 21.2.2018.
 */

public class UsersiteActivity extends AppCompatActivity {

    private Button mSearch;
    private Button mGive;
    private Button mReceive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usersite);

        mSearch = (Button) findViewById(R.id.search_button);
        mGive = (Button) findViewById(R.id.give_button);
        mReceive = (Button) findViewById(R.id.receive_button);


        mSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Toast.makeText(UsersiteActivity.this,
                            R.string.clickSearch,
                            Toast.LENGTH_SHORT).show();

            }
        });
    }



}
