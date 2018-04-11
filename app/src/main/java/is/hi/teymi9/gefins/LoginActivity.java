package is.hi.teymi9.gefins;

import android.content.Intent;
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
    private static UserService userService = new UserService();


    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }

    public static UserService getUserService() {
        return userService;
    }

    /**
     * Ræsir UsersiteActivity þegar að innskráning hefur tekist
     */
    public void loginWasSucessful() {
        Intent intent = new Intent(this, UsersiteActivity.class);
        startActivity(intent);
        onBackPressed();
    }

    /**
     * Ræsir AdminActivity þegar að innskráning fyrir admin hefur tekist
     */
    public void adminLogin() {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
        onBackPressed();
    }
}

