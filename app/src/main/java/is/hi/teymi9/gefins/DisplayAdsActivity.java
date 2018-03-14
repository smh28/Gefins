package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;

/**
 * Activity í Gefins sem sýnir lista af auglýsingum
 * author Sandra
 * @version 1.0
 */

public class DisplayAdsActivity extends SingleFragmentActivity {

    public static AdService adService = new AdService();

    @Override
    protected Fragment createFragment() {return new DisplayAdsFragment(); }

    public static AdService getAdService() {
        return adService;
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, DisplayAdsActivity.class);
        return intent;
    }

}

