package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.List;

import is.hi.teymi9.gefins.model.Message;

/**
 * Fragment fyrir viðmótið í OutboxActivity og virknina þar.
 *
 * @author Einar
 * @version 1.0
 */

public class OutboxFragment extends Fragment {

    //TextView content;
    List<Message> allMessages;
    // Tilbaka takki
    private Button mBack;
    // Takki fyrir útbox
    private Button mOutbox;
    // Titill síðu
    private TextView mTitle;

    private RecyclerView mOutboxRecyclerView;
    private MessageAdapter mAdapter;

    public OutboxFragment() {
        allMessages = WriteMessageActivity.getMessageService().getOutboxMessages();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inbox, container, false);   //var R.layout.fragment_display_ads

        mOutboxRecyclerView = (RecyclerView)v.findViewById(R.id.inbox_recycler_view);
        mOutboxRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTitle = (TextView) v.findViewById(R.id.inbox_text);
        mTitle.setText(R.string.outbox);

        updateUI();

        mBack = (Button) v.findViewById(R.id.inboxTilbaka);
        mOutbox = (Button) v.findViewById(R.id.outbox_button);
        mOutbox.setEnabled(false);
        mOutbox.setVisibility(View.GONE);

        //Sendir notanda tilbaka á usersite síðuna
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UsersiteActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    private void updateUI() {
        List<Message> messages = WriteMessageActivity.getMessageService().getOutboxMessages();
        mAdapter = new MessageAdapter(messages);
        mOutboxRecyclerView.setAdapter(mAdapter);
    }

    private class MessageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mSubjectTextView;
        private TextView mRecipientTextView;
        private TextView mDateTextView;
        private Message mMessage;

        public MessageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_message, parent, false));
            itemView.setOnClickListener(this);

            mSubjectTextView = (TextView) itemView.findViewById(R.id.message_subject);
            mRecipientTextView = (TextView) itemView.findViewById(R.id.message_sender);
            mDateTextView = (TextView) itemView.findViewById(R.id.message_date);
        }

        public void bind(Message m) {
            mMessage = m;
            mSubjectTextView.setText(mMessage.getSubject());
            mRecipientTextView.setText(mMessage.getRecipient());
            mDateTextView.setText(mMessage.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Intent intent = MessageDetailsActivity.newIntent(getActivity(), mMessage, false);
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

