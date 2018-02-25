package is.hi.teymi9.gefins;

import android.content.Context;
import android.support.v4.app.Fragment;
import is.hi.teymi9.gefins.service.UserService;

public class LoginActivity extends SingleFragmentActivity {
    private static LoginActivity sLoginActivity;

    public static UserService userService = new UserService();

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }

    public static UserService getUserService() {
        return userService;
    }

}

