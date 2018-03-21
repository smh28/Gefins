package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;

/**
 * Activity í Gefins sem sýnir lista af skilaboðum
 *
 * @author Einar
 * @version 1.0
 */

public class InboxActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {return new InboxFragment(); }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru og bætir
     * þeim við
     * @param packageContext Context þess activity sem ræsir InboxActivity
     * @return Nýtt intent fyrir InboxActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, InboxActivity.class);
        return intent;
    }

    /**
     * býr til og ræsir activity til að sýna lista af skilaboðum
     */
    public void displayOutbox() {
        Intent intent = OutboxActivity.newIntent(this);
        startActivity(intent);
    }

}

