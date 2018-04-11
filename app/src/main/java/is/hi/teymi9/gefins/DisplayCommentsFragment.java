package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.Comment;

/**
 * Fragment fyrir viðmótið í DisplayCommentActivity og virknina þar.
 *
 * @author Ólöf Fríða
 * @date March 2018
 * @version 1.0
 */

public class DisplayCommentsFragment extends Fragment {

    // viðmót fyrir listann af athugasemdum
    private ListView mComments;
    // Tilbaka takki
    private Button mBack;
    // id á auglýsingunni sem verið er að skoða
    private Ad ad;
    // allar athugasemdir sem tengjast auglýsingunni
    private List<Comment> lComments;
    // Takki til að skrifa nýja athugasemd
    private Button mNewComment;
    // takki til að staðfesta nýja athugasemdinga
    private Button mSubmit;
    // Edit Text svo notandi geti skrifað athugasemd
    private EditText mCommentText;
    // Texti sem segir notanda að skrifa athugasemd
    private TextView mCommentTV;
    // strengur sem inniheldur auglýsingu
    private Ad mAd;
    // Strengur sem inniheldur notendanafn
    private String mUsername;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_display_comments,
                container, false);

        mBack = (Button) v.findViewById(R.id.singleAdTilbaka);
        mComments = (ListView) v.findViewById(R.id.commentList);
        mNewComment = (Button) v.findViewById(R.id.newComment);
        mCommentText = (EditText) v.findViewById(R.id.newCommentText);
        mCommentTV = (TextView) v.findViewById(R.id.commentTV);
        mSubmit = (Button) v.findViewById(R.id.confirmComment);
        mUsername = LoginActivity.getUserService().getCurrentUser().getUsername();
        mAd = DisplaySingleAdActivity.adService.getCurrentAd();


        mCommentTV.setEnabled(false);
        mCommentTV.setVisibility(View.GONE);
        mCommentText.setEnabled(false);
        mCommentText.setVisibility(View.GONE);
        mSubmit.setEnabled(false);
        mSubmit.setVisibility(View.GONE);

        // nær í allar athugasemdir
        lComments = DisplayCommentActivity.commentService.getAllComments();

        String[] userComment = new String[lComments.size()];
        String[] comment = new String[lComments.size()];
        UUID[] commentId = new UUID[lComments.size()];

        // stillir userComment, comment og commentId
        int i = 0;
        for (Comment c: lComments) {
            userComment[i] = c.getUsername();
            comment[i] = c.getComment();
            commentId[i] = c.getId();
            i++;
        }

        // stillir custom listann
        CustomCommentList customCommentList = new CustomCommentList(getActivity(),userComment, comment,commentId);
        mComments.setAdapter(customCommentList);

        //Sendir notanda tilbaka á usersite síðuna
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommentTV.setEnabled(false);
                mCommentTV.setVisibility(View.GONE);
                mCommentText.setEnabled(false);
                mCommentText.setVisibility(View.GONE);
                mSubmit.setEnabled(false);
                mSubmit.setVisibility(View.GONE);

                Comment newComment = new Comment(mUsername, mCommentText.getText().toString(),mAd);

                System.out.println("Athugasemdin er " + newComment.getComment() + " Userinn er " + newComment.getUsername() + "Auglýsingin er " + newComment.getAd());

                String message = DisplayCommentActivity.commentService.addComment(newComment,getActivity());
                Toast.makeText(getActivity(),
                        message,
                        Toast.LENGTH_SHORT).show();

                lComments.add(newComment);

                String[] userComment = new String[lComments.size()];
                String[] comment = new String[lComments.size()];
                UUID[] commentId = new UUID[lComments.size()];

                // stillir userComment, comment og commentId
                int i = 0;
                for (Comment c: lComments) {
                    userComment[i] = c.getUsername();
                    comment[i] = c.getComment();
                    commentId[i] = c.getId();
                    i++;
                }
                // stillir custom listann
                CustomCommentList customCommentList = new CustomCommentList(getActivity(),userComment, comment,commentId);
                mComments.setAdapter(customCommentList);
            }
        });

        // Takki sem leyfir notanda að skrifa nýja athugasemd
        mNewComment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mNewComment.setEnabled(false);
                mNewComment.setVisibility(View.GONE);
                mCommentTV.setEnabled(true);
                mCommentTV.setVisibility(View.VISIBLE);
                mCommentText.setEnabled(true);
                mCommentText.setVisibility(View.VISIBLE);
                mSubmit.setEnabled(true);
                mSubmit.setVisibility(View.VISIBLE);
            }
        });

        return v;
    }
}
