package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;

/**
 * Activity í Gefins til þess að breyta innihaldi auglýsinga
 *
 * @author Sandra
 * @version 1.0
 */

public class EditAdActivity extends SingleFragmentActivity {

    //public static AdService adService = new AdService();
    public static AdService adService = DisplayAdsActivity.getAdService();

    @Override
    protected Fragment createFragment() {
        return new EditAdFragment();
    }

    public static AdService getAdService() {
        return adService;
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

    /**
     * Ræsir DisplayAdsActivity þegar að uppfærsla auglýsingar hefur tekist
     */
    public void updateAdWasSuccessful(){
        Intent intent = new Intent(this, DisplayAdsActivity.class);
        startActivity(intent);
    }

}
