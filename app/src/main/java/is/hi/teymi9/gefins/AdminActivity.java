package is.hi.teymi9.gefins;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;
import is.hi.teymi9.gefins.service.UserService;

/**
 * Activity í Gefins sem sýnir upphafssíðu kerfisstjóra
 *
 * @author Ólöf Fríða og Kristín María
 * @version 1.0
 * @date March 2018
 */

public class AdminActivity extends SingleFragmentActivity {

    public static UserService userService = new UserService();

    public static UserService getUserService() { return userService; }

    public static AdService adService = new AdService();

    public static AdService getAdService() { return adService; }

    @Override
    protected Fragment createFragment() {
        return new AdminFragment();
    }
    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru og bætir
     * þeim við
     * @param packageContext Context þess activity sem ræsir AdminActivity
     * @return Nýtt intent fyrir AdminActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AdminActivity.class);
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
     * Ræsir activity til að sýna lista yfir auglýsingar
     */
    /*
    public void displayUsers() {
        Intent intent = new Intent(this, UsersiteActivity.class);
        startActivity(intent);
    }
    */

    /**
     * Býr til Dialog ef ýtt er á back takka frá adminsíðu(og skráir admin þá út) og spyr
     * hvort admin sé viss um að hann vilji skrá sig út
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Útskráning")
                .setMessage("Ertu viss um að þú viljir skrá þig út?")
                .setPositiveButton("Já", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Nei", null)
                .show();
    }

}
