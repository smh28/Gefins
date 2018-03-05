package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import is.hi.teymi9.gefins.model.Comment;


/**
 * Fragment fyrir viðmótið í AddAdActivity og virknina þar.
 *
 * @author Ólöf Fríða
 * @version
 */

public class AddCommentFragment extends Fragment {


    // þurfum að finna current auglýsingu
    // Strengur sem inniheldur notendanafn
    private String mUsername;
    // Strengur sem inniheldur athugasemdina
    private EditText mComment;
    // Takki sem staðfestir
    private Button mSubmit;
    // Takki sem fer til baka
    private Button mCancel;
    // strengur sem inniheldur auglýsingu
    private int mAd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_comment, container, false);

        mSubmit = (Button) v.findViewById(R.id.confirm);
        mCancel = (Button) v.findViewById(R.id.tilbaka);
        mComment = (EditText) v.findViewById(R.id.commentText);
        mUsername = LoginActivity.getUserService().getCurrentUser().getUsername();
        mAd = AddAdActivity.getAdService().getCurrentAd().getAdID();

        // Villumeðhöndlun á comment ( má ekki vera tómt )

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment newComment = new Comment(mUsername, mComment.toString(),mAd);
                addCommentActivity.getCommentService().addAd(newComment);
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            // skila auglýsingunni
            @Override
            public void onClick(View v) {

            }
        });


        return v;
    }

}


