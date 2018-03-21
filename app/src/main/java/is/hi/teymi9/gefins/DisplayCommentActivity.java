package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;
import is.hi.teymi9.gefins.service.CommentService;

/**
 * Activity í Gefins sem sýnir athugasemdir fyrir valda auglýsingu
 * author Ólöf Fríða
 * @version 1.0
 */


public class DisplayCommentActivity extends SingleFragmentActivity {

    public static CommentService commentService = new CommentService();
    public static AdService adService = new AdService();

    public static AdService getAdService() {
        return adService;
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
