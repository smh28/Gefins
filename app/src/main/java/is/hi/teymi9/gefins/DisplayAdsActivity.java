package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;

/**
 * Activity í Gefins sem sýnir lista af auglýsingum
 *
 * @author Sandra
 * @version 1.0
 * @date
 */

public class DisplayAdsActivity extends SingleFragmentActivity {

    // adService breyta
    public static AdService adService = new AdService();

    @Override
    protected Fragment createFragment() {return new DisplayAdsFragment(); }

    // skilar adService
    public static AdService getAdService() {
        return adService;
    }
    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru og bætir
     * þeim við
     * @param packageContext Context þess activity sem ræsir AddAdActivity
     * @return Nýtt intent fyrir AddAdActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, DisplayAdsActivity.class);
        return intent;
    }

}

