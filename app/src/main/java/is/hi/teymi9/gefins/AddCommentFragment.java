package is.hi.teymi9.gefins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.hi.teymi9.gefins.model.Ad;
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
    private Ad mAd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addCommentActivity.getCommentService().setAddCommentActivity(getActivity());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_comment, container, false);
        mSubmit = (Button) v.findViewById(R.id.confirm);
        mCancel = (Button) v.findViewById(R.id.tilbaka);
        mComment = (EditText) v.findViewById(R.id.commentText);
        mUsername = LoginActivity.getUserService().getCurrentUser().getUsername();
        mAd = DisplaySingleAdActivity.adService.getCurrentAd();

        // Villumeðhöndlun á comment ( má ekki vera tómt )

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment newComment = new Comment(mUsername, mComment.getText().toString(),mAd);
                String message = addCommentActivity.getCommentService().addComment(newComment);
                Toast.makeText(getActivity(),
                        message,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), DisplaySingleAdActivity.class);
                intent.putExtra("name", mAd.getAdName());
                intent.putExtra("giveOrTake", mAd.getGiveOrTake());
                intent.putExtra("type", mAd.getAdType());
                intent.putExtra("typeOfType", mAd.getAdTypeOfType());
                intent.putExtra("color", mAd.getAdColor());
                intent.putExtra("description", mAd.getAdDescription());
                intent.putExtra("location", mAd.getAdLocation());
                intent.putExtra("username", mAd.getAdUsername());
                startActivity(intent);
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


