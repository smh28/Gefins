package is.hi.teymi9.gefins;

import android.support.v4.app.Fragment;
import is.hi.teymi9.gefins.service.UserService;

/**
 * Upphafsactivity Gefins.
 * Sýnir login skjá þar sem notandi getur skráð sig inn
 *
 * Gefins er smáforrit þar sem hægt að að skrá hlut sem fólk vill gefa
 * og senda inn auglýsingar eftir  hlutum sem fólk vill auglýsa eftir.
 *
 * @author Teymi 9
 * @version 1.0
 */

public class LoginActivity extends SingleFragmentActivity {

    // Static UserService sem er sameiginlegt fyrir allt forritið
    public static UserService userService = new UserService();

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }

    public static UserService getUserService() {
        return userService;
    }

}

