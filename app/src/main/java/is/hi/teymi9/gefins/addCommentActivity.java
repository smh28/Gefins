package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import is.hi.teymi9.gefins.service.AdService;
import is.hi.teymi9.gefins.service.CommentService;


/**
 * Activity í Gefins til þess að bæta við nýju commenti á auglýsingu
 *
 * @author Ólöf Fríða
 * @version 1.0
 * @date March 2018
 */

public class addCommentActivity extends SingleFragmentActivity {

    // commentService breyta
    public static CommentService commentService = new CommentService();

    @Override
    protected Fragment createFragment() {
        return new AddCommentFragment();
    }
    // Skilar commentService
    public static CommentService getCommentService() {
        return commentService;
    }
    /**
     * "Custom" fall til að búa til nýtt Intent sem tekur við "Extras" ef einhver eru og bætir
     * þeim við
     * @param packageContext Context þess activity sem ræsir addCommentActivity
     * @return Nýtt intent fyrir addCommentActivity
     */
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, addCommentActivity.class);
        return intent;
    }

    /**
     * Callback þegar bætt er við athugasemd. Fer aftur í lista af athugasemdum.
     */
    public void commentAddedSuccessfully() {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        startActivity(intent);
    }
}
