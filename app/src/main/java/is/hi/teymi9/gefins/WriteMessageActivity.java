package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.MessageService;

/**
 * Activity í Gefins til þess búa að skrifa ný einkaskilaboð og senda þau
 *
 * @author Einar
 * @version 1.0
 */

public class WriteMessageActivity extends SingleFragmentActivity {

    // Static MessageService sem er sameiginlegt fyrir allt forritið
    private static MessageService messageService = new MessageService();

    public static MessageService getMessageService() {
        return messageService;
    }

    @Override
    protected Fragment createFragment() {
        return new WriteMessageFragment();
    }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru
     * og bætir þeim við
     *
     * @param packageContext Context þess activity sem ræsir WriteMessageActivity
     * @return Nýtt intent fyrir WriteMessageActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, WriteMessageActivity.class);
        return intent;
    }

    public void sendMessageSuccessful() {
        Intent intent = UsersiteActivity.newIntent(this);
        startActivity(intent);
    }


}
