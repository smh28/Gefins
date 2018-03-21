package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import is.hi.teymi9.gefins.model.User;
import is.hi.teymi9.gefins.service.UserService;

/**
 * Fragment fyrir viðmótið í MessageDetailsActivity og virknina þar.
 *
 * @author Einar
 * @version 1.0
 */

public class MessageDetailsFragment extends Fragment {

    // Tilbaka takki
    private Button mBack;
    private Button mReply;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_message_details,
                container, false);
        TextView tvSubject = (TextView)v.findViewById((R.id.subject));
        TextView tvSender = (TextView)v.findViewById((R.id.sender));
        TextView tvDate = (TextView)v.findViewById((R.id.date));
        TextView tvContent = (TextView)v.findViewById((R.id.content));

        mBack = (Button) v.findViewById(R.id.messageDetailsTilbaka);
        mReply = (Button) v.findViewById(R.id.reply);

        Bundle bundle = getActivity().getIntent().getExtras();

        if(bundle != null) {
            String subject = bundle.getString("subject");
            String sender = bundle.getString("sender");
            String date = bundle.getString("date");
            String content = bundle.getString("content");
            boolean inMessage = bundle.getBoolean("inMessage");
            tvSubject.setText(subject);
            if(inMessage) {
                tvSender.setText("Sendandi: " + sender);
            }
            else {
                tvSender.setText("Viðtakandi: " + sender);
            }
            tvDate.setText("Dagsetning: " + date);
            tvContent.setText("Skilaboð: \n" + content);

            if(LoginActivity.getUserService().getCurrentUser().getUsername().compareTo(sender) == 0) {
                // aftengi reply takka ef sendandi er sami og viðtakandi
                mReply.setEnabled(false);
                mReply.setVisibility(View.GONE);
            }
            else {
                mReply.setEnabled(true);
            }
        }

        //Sendir notanda tilbaka á lista yfir skilaboð
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // fer til bara í inbox ef in message, annars aftur í outbox
                boolean inMessage = true;
                if(bundle != null) {
                    inMessage = bundle.getBoolean("inMessage");
                }
                if(inMessage) {
                    Intent intent = new Intent(getActivity(), InboxActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getActivity(), OutboxActivity.class);
                    startActivity(intent);
                }
            }
        });

        //Sendir notanda á síðu fyrir ný skilaboð
        mReply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(bundle != null) {
                    Intent intent = WriteMessageActivity.newIntent(getActivity(), bundle.getString("sender"));
                    startActivity(intent);
                }
            }
        });

        return v;
    }
}
