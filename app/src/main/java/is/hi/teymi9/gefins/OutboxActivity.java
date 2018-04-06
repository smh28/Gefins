package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Activity í Gefins sem sýnir lista af skilaboðum
 *
 * @author Einar
 * @version 1.0
 * @date
 */

public class OutboxActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {return new OutboxFragment(); }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru og bætir
     * þeim við
     * @param packageContext Context þess activity sem ræsir OutoxActivity
     * @return Nýtt intent fyrir OutboxActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, OutboxActivity.class);
        return intent;
    }

}

