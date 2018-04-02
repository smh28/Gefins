package is.hi.teymi9.gefins;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;

/**
 * Activity í Gefins til þess að gera leit að auglýsingum samkvæmt flokkum
 *
 * @author Sandra
 * @version 1.0
 */

public class SearchTypeActivity extends SingleFragmentActivity {

    public static AdService adService = new AdService();

    @Override
    protected Fragment createFragment() {
        return new SearchTypeFragment();
    }

    public static AdService getAdService() {
        return adService;
    }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru og bætir
     * þeim við
     * @param packageContext Context þess activity sem ræsir SearchTypeActivity
     * @return Nýtt intent fyrir SearchTypeActivity
     */    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, SearchTypeActivity.class);
        return intent;
    }

    /**
     * Býr til og ræsir activity til að sýna lista yfir auglýsingar
     */
    public void displayAds() {
        Intent intent = new Intent(this, DisplayAdsActivity.class);
        startActivity(intent);
    }

}
