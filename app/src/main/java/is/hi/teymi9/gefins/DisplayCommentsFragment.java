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

import is.hi.teymi9.gefins.model.Comment;

/**
 * Fragment fyrir viðmótið í DisplayCommentActivity og virknina þar.
 *
 * @author Ólöf Fríða
 * @version 1.0
 */

public class DisplayCommentsFragment extends Fragment {

    // viðmót fyrir listann af athugasemdum
    private ListView mComments;
    // Tilbaka takki
    private Button mBack;
    // id á auglýsingunni sem verið er að skoða
    private String adId;
    // allar athugasemdir sem tengjast auglýsingunni
    private List<Comment> lComments;

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
        Bundle bundle = getActivity().getIntent().getExtras();
        adId = bundle.getString("id");
        int id = Integer.parseInt(adId);

        lComments = DisplayCommentActivity.commentService.getCommentsByAdId(id);

        String[] userComment = new String[lComments.size()];
        String[] comment = new String[lComments.size()];

        // Sækja öll comment sem tengjast þessari auglýsingu með gefnu id


        int i = 0;
        for (Comment c: lComments) {
            userComment[i] = c.getUsername();
            comment[i] = c.getComment();
            i++;
        }

        CustomCommentList customCommentList = new CustomCommentList(getActivity(),userComment, comment );

        mComments.setAdapter(customCommentList);

        //Sendir notanda tilbaka á usersite síðuna
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DisplayAdsActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
