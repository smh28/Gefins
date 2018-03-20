package is.hi.teymi9.gefins;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import is.hi.teymi9.gefins.model.Message;

/**
 * Fragment fyrir viðmótið í InboxActivity og virknina þar.
 *
 * @author Einar
 * @version 1.0
 */

public class InboxFragment extends Fragment {

    //TextView content;
    List<Message> allMessages;
    // Tilbaka takki
    private Button mBack;
    // Takki fyrir útbox
    private Button mOutbox;

    private RecyclerView mInboxRecyclerView;
    private MessageAdapter mAdapter;

    public InboxFragment() {
        allMessages = WriteMessageActivity.getMessageService().getMessages();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inbox, container, false);   //var R.layout.fragment_display_ads

        mInboxRecyclerView = (RecyclerView)v.findViewById(R.id.inbox_recycler_view);
        mInboxRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        mBack = (Button) v.findViewById(R.id.inboxTilbaka);
        mOutbox = (Button) v.findViewById(R.id.outbox_button);

        //Sendir notanda tilbaka á usersite síðuna
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UsersiteActivity.class);
                startActivity(intent);
            }
        });

        //Sendir notanda í outboxið
        mOutbox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                WriteMessageActivity
                        .getMessageService()
                        .getMySentMessages(LoginActivity
                                .getUserService()
                                .getCurrentUser(), getActivity());
            }
        });

        return v;
    }

    private void updateUI() {
        List<Message> messages = WriteMessageActivity.getMessageService().getMessages();
        mAdapter = new MessageAdapter(messages);
        mInboxRecyclerView.setAdapter(mAdapter);
    }

    private class MessageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mSubjectTextView;
        private TextView mSenderTextView;
        private TextView mDateTextView;
        private Message mMessage;

        public MessageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_message, parent, false));
            itemView.setOnClickListener(this);

            mSubjectTextView = (TextView) itemView.findViewById(R.id.message_subject);
            mSenderTextView = (TextView) itemView.findViewById(R.id.message_sender);
            mDateTextView = (TextView) itemView.findViewById(R.id.message_date);
        }

        public void bind(Message m) {
            mMessage = m;
            mSubjectTextView.setText(mMessage.getSubject());
            mSenderTextView.setText(mMessage.getSender());
            mDateTextView.setText(mMessage.getDate().toString());
            if(!m.isRead()) {
                // breyta útlitinu á textanum ef ekki er búið að lesa skilaboðin
                mSubjectTextView.setTypeface(mSubjectTextView.getTypeface(), Typeface.BOLD_ITALIC);
                mSenderTextView.setTypeface(mSenderTextView.getTypeface(), Typeface.BOLD_ITALIC);
                mDateTextView.setTypeface(mDateTextView.getTypeface(), Typeface.BOLD_ITALIC);
            }
        }

        @Override
        public void onClick(View view) {
            if(!mMessage.isRead()) {
                // ef skilaboð eru ekki þegar lesin þá merkja þau sem lesin
                // og senda skilaboð um það á bakenda
                mMessage.setRead(true);
                WriteMessageActivity.getMessageService().sendMessage(mMessage, getActivity());
            }
            Intent intent = MessageDetailsActivity.newIntent(getActivity(), mMessage, true);
            startActivity(intent);
        }
    }

    private class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {
        private List<Message> mMessages;
        public MessageAdapter(List<Message> messages) {
            mMessages = messages;
        }

        @Override
        public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MessageHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MessageHolder holder, int position) {
            Message message = mMessages.get(position);
            holder.bind(message);
        }

        @Override
        public int getItemCount() {
            return mMessages.size();
        }
    }
}

