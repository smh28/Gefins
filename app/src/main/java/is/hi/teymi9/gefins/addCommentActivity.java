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
 */

public class addCommentActivity extends SingleFragmentActivity {

    public static CommentService commentService = new CommentService();

    @Override
    protected Fragment createFragment() {
        return new AddCommentFragment();
    }

    public static CommentService getCommentService() {
        return commentService;
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, addCommentActivity.class);
        return intent;
    }
}
