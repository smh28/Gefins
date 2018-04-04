package is.hi.teymi9.gefins;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;

/**
 * Activity í Gefins sem sýnir upplýsingar yfir staka auglýsingu
 * author Sandra
 * @version 1.0
 */


public class DisplaySingleAdActivity extends SingleFragmentActivity {

    public static AdService adService = DisplayAdsActivity.getAdService();

    @Override
    protected Fragment createFragment() {
        return new DisplaySingleAdFragment();
    }


    /**
     * Býr til að ræsir activity til að sýna lista yfir auglýsingar
     */
    public void displayAdComments() {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        startActivity(intent);
    }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru
     * og bætir þeim við
     *
     * @param packageContext Context þess activity sem ræsir DisplaySingleAdActivity
     * @return Nýtt intent fyrir DisplaySingleAdActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, DisplaySingleAdActivity.class);
        return intent;
    }

    /**
     * Callback þegar auglýsingu er eytt. Fer aftur í lista af auglýsingum.
     */
    public void adDeletedSuccessfully() {
        Intent intent = new Intent(this, DisplayAdsActivity.class);
        startActivity(intent);
    }

}
