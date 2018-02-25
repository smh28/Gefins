package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import is.hi.teymi9.gefins.service.UserService;

/**
 * Created by Sandra on 21.2.2018.
 */

public class UsersiteActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new UsersiteFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, UsersiteActivity.class);
        return intent;
    }

}
