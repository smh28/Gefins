package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;

/**
 * Activity í Gefins sem sýnir upplýsingar yfir ákveðin skilaboð
 * author Einar
 * @version 1.0
 */


public class MessageDetailsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MessageDetailsFragment();
    }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru
     * og bætir þeim við
     *
     * @param packageContext Context þess activity sem ræsir MessageDetailsActivity
     * @return Nýtt intent fyrir MessageDetailsActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MessageDetailsActivity.class);
        return intent;
    }

}
