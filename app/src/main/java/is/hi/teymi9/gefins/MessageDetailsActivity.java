package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.model.Message;
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
    public static Intent newIntent(Context packageContext, Message m, boolean inMessage) {
        Intent intent = new Intent(packageContext, MessageDetailsActivity.class);
        intent.putExtra("subject", m.getSubject());
        if(inMessage) {
            intent.putExtra("sender", m.getSender());
        }
        else {
            intent.putExtra("sender", m.getRecipient());
        }
        intent.putExtra("date", m.getDate().toString());
        intent.putExtra("content", m.getMessage());
        intent.putExtra("inMessage", inMessage);
        return intent;
    }

}
