package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Activity í Gefins sem sýnir síðu þar sem kerfisstjóri getur eytt út notendum
 *
 * @author Kristín María
 * @version 1.0
 */

public class AdminDeleteUserActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() { return new AdminDeleteUserFragment(); }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru og bætir
     * þeim við
     * @param packageContext Context þess activity sem ræsir AdminDeleteUserActivity
     * @return Nýtt intent fyrir AdminDeleteUserActivity
     */

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AdminDeleteUserActivity.class);
        return intent;
    }


    /**
     * Callback þegar notanda er eytt. Fer aftur í lista af notendum.
     */
    public void userDeletedSuccessfully() {
        Intent intent = new Intent(this, AdminDeleteUserActivity.class);
        startActivity(intent);
    }

}