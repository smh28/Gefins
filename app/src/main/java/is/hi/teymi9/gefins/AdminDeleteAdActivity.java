package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Activity í Gefins sem sýnir síðu þar sem kerfisstjóri getur eytt út auglýsingum
 *
 * @author Kristín María
 * @version 1.0
 */

public class AdminDeleteAdActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() { return new AdminDeleteAdFragment(); }

     /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru og bætir
     * þeim við
     * @param packageContext Context þess activity sem ræsir AdminDeleteAdActivity
     * @return Nýtt intent fyrir AdminDeleteAdActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AdminDeleteAdActivity.class);
        return intent;
    }

    /**
     * Callback þegar auglýsingu er eytt. Fer aftur í lista af auglýsingum.
     */
    public void adDeletedSuccessfully() {
        Intent intent = new Intent(this, AdminDeleteAdActivity.class);
        startActivity(intent);
    }
}
