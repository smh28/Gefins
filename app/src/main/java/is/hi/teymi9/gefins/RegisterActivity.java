package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.UserService;

/**
 * Created by Einar on 25.2.2018.
 */

public class RegisterActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RegisterFragment();
    }

    public static Intent newIntent(Context packageContext, UserService userService) {
        Intent intent = new Intent(packageContext, RegisterActivity.class);
        return intent;
    }
}
