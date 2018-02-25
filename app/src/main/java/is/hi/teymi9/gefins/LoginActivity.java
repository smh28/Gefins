package is.hi.teymi9.gefins;

import android.support.v4.app.Fragment;
import is.hi.teymi9.gefins.service.UserService;

public class LoginActivity extends SingleFragmentActivity {

    public static UserService userService = new UserService();

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }

    public static UserService getUserService() {
        return userService;
    }

}

