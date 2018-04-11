package is.hi.teymi9.gefins;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
 * @author Sandra
 * @version 1.0
 * @date
 */

public class UsersiteActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new UsersiteFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addUserNameToTitle(String name){
        setTitle(getTitle() + " - " + name);
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
     * Býr til og ræsir activity til að sýna lista yfir auglýsingar
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

    /**
     * býr til og ræsir activity til að sýna lista af skilaboðum
     */
    public void displayOutbox() {
        Intent intent = OutboxActivity.newIntent(this);
        startActivity(intent);
    }


    /**
     * Býr til Dialog ef ýtt er á back takka frá notendasíðu(og skráir notanda þá út) og spyr
     * hvort notandi sé viss um að hann vilji skrá sig út
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
                        Intent intent = new Intent(UsersiteActivity.this, LoginActivity.class);
                        startActivity(intent);
                        onBackPressed();
                    }

                })
                .setNegativeButton("Nei", null)
                .show();
    }

}
