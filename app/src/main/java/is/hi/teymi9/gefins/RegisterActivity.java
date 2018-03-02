package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.UserService;

/**
 * Activity í Gefins til þess búa til nýjan notanda með því að slá inn upplýsingar
 *
 * @author Einar
 * @version 1.0
 */

public class RegisterActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RegisterFragment();
    }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru
     * og bætir þeim við
     *
     * @param packageContext Context þess activity sem ræsir RegisterActivity
     * @return Nýtt intent fyrir RegisterActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, RegisterActivity.class);
        return intent;
    }

    /**
     * Ræsir UsersiteActivity þegar að notandi hefur verið innskráður
     */
    public void createUserWasSucessful() {
        Intent intent = new Intent(this, UsersiteActivity.class);
        startActivity(intent);
    }
}
