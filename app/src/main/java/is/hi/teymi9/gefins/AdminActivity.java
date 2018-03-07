package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Activity í Gefins sem sýnir upphafssíðu notanda
 *
 * @author Ólöf Fríða
 * @version 1.0
 */

public class AdminActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AdminFragment();
    }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru og bætir
     * þeim við
     * @param packageContext Context þess activity sem ræsir UsersiteActivity
     * @return Nýtt intent fyrir UsersiteActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AdminActivity.class);
        return intent;
    }


}