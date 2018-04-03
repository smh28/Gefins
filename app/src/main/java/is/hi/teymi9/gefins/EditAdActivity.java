package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Activity í Gefins til þess að breyta innihaldi auglýsinga
 *
 * @author Sandra
 * @version 1.0
 */

public class EditAdActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new EditAdFragment();
    }


    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru
     * og bætir þeim við
     *
     * @param packageContext Context þess activity sem ræsir EditAdActivity
     * @return Nýtt intent fyrir EditAdActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, EditAdActivity.class);
        return intent;
    }


}
