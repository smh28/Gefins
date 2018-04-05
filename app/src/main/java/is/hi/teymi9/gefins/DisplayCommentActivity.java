package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;
import is.hi.teymi9.gefins.service.CommentService;

/**
 * Activity í Gefins sem sýnir athugasemdir fyrir valda auglýsingu
 * @author Ólöf Fríða
 * @date March 2018
 * @version 1.0
 */


public class DisplayCommentActivity extends SingleFragmentActivity {

    // commentService breyta
    public static CommentService commentService = new CommentService();
    // adService breyta
    public static AdService adService = new AdService();

    // skilar adService
    public static AdService getAdService() {
        return adService;
    }

    /**
     * Býr til að ræsir activity til að sýna lista yfir auglýsingar
     */
    public void displayAdComments() {
        Intent intent = new Intent(this, DisplayAdsActivity.class);
        startActivity(intent);
    }


    @Override
    protected Fragment createFragment() {
        return new DisplayCommentsFragment();
    }

    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru
     * og bætir þeim við
     *
     * @param packageContext Context þess activity sem ræsir DisplaySingleAdActivity
     * @return Nýtt intent fyrir DisplaySingleAdActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, DisplayCommentActivity.class);
        return intent;
    }

}
