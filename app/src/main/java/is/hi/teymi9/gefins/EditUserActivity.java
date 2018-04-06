package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Activity í Gefins til þess að breyta notendaupplýsingum
 *
 * @author Einar
 * @version 1.0
 * @date
 */

public class EditUserActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new EditUserFragment();
    }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru
     * og bætir þeim við
     *
     * @param packageContext Context þess activity sem ræsir EditUserActivity
     * @return Nýtt intent fyrir EditUserActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, EditUserActivity.class);
        return intent;
    }

    /**
     * Ræsir UsersiteActivity þegar að uppfærsla notendaupplýsinga hefur tekist
     */
    public void updateUserWasSuccessful(){
        Intent intent = new Intent(this, UsersiteActivity.class);
        startActivity(intent);
    }

}
