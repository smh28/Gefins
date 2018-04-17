package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import is.hi.teymi9.gefins.service.AdService;


/**
 * Activity í Gefins til þess að bæta við nýrri auglýsingu
 *
 * @author Kristín María
 * @date
 * @version 1.0
 */

public class AddAdActivity extends SingleFragmentActivity {

    // adService breytan
    public static AdService adService = DisplayAdsActivity.getAdService();

    @Override
    protected Fragment createFragment() {
        return new AddAdFragment();
    }

    /**
     * Skilar adService svo hægt er að nota hana
     * @return
     */
    public static AdService getAdService() {
        return adService;
    }
    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru og bætir
     * þeim við
     * @param packageContext Context þess activity sem ræsir AddAdActivity
     * @return Nýtt intent fyrir AddAdActivity
     */    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AddAdActivity.class);
        return intent;
    }

    public void addAdSuccessful() {
         onBackPressed();
    }
}
