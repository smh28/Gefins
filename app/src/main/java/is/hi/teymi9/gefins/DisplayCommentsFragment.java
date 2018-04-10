package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
                /*Intent intent = new Intent(getActivity(), DisplayAdsActivity.class);
                startActivity(intent);*/
                getActivity().onBackPressed();
            }

        });

        // Takki sem leyfir notanda að skrifa nýja athugasemd
        mNewComment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                Intent intent = new Intent(getActivity(), addCommentActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
