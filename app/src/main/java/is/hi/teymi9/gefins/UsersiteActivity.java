package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import is.hi.teymi9.gefins.service.UserService;

/**
 * Activity í Gefins sem sýnir upphafssíðu notanda
 *
 * @author Sanda
 * @version 1.0
 */

public class UsersiteActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new UsersiteFragment();
    }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru
     * og bætir þeim við
     *
     * @param packageContext Context þess activity sem ræsir UsersiteActivity
     * @return Nýtt intent fyrir UsersiteActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, UsersiteActivity.class);
        return intent;
    }

    /**
     * Ræsir activity til að sýna lista yfir auglýsingar
     */
    public void displayAds() {
        Intent intent = new Intent(this, DisplayAdsActivity.class);
        startActivity(intent);
    }

    /**
     * Býr til að ræsir activity til að sýna lista yfir auglýsingar sem hefur ákveðinn höfund
     */
    public void displayUserAds() {
        Intent intent = new Intent(this, DisplayAdsActivity.class);
        startActivity(intent);
    }

    /**
     * býr til og ræsir activity til að sýna lista af skilaboðum
     */
    public void displayInbox() {
        Intent intent = InboxActivity.newIntent(this);
        startActivity(intent);
    }

}
