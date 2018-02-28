package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import is.hi.teymi9.gefins.service.AdService;


/**
 * Activity í Gefins til þess að bæta við nýrri auglýsingu
 *
 * @author Kristín María
 * @version 1.0
 */

public class AddAdActivity extends SingleFragmentActivity {

    public static AdService adService = new AdService();

    @Override
    protected Fragment createFragment() {
        return new AddAdFragment();
    }

    public static AdService getAdService() {
        return adService;
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AddAdActivity.class);
        return intent;
    }
}
