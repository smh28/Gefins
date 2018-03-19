package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import is.hi.teymi9.gefins.model.Message;

/**
 * Fragment fyrir viðmótið í WriteMessageActivity og virknina þar.
 *
 * @author Einar
 * @version 1.0
 */

public class WriteMessageFragment extends Fragment {

    // Takki til að senda skilaboð
    private Button mSend;
    // Textasvið fyrir viðtakanda
    private EditText mTo;
    // Textasvið fyrir viðfangsefni
    private EditText mSubject;
    // Textasvið fyrir skilaboð
    private EditText mMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WriteMessageActivity.getMessageService().setWriteMessageActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write_message, container, false);

        mTo = (EditText) v.findViewById(R.id.to);
        mSubject = (EditText) v.findViewById(R.id.subject);
        mMessage = (EditText) v.findViewById(R.id.message);

        mSend = (Button) v.findViewById(R.id.send);

        mSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String from = LoginActivity.getUserService().getCurrentUser().getUsername();
                String to = mTo.getText().toString();
                String subject = mSubject.getText().toString();
                String message = mMessage.getText().toString();
                Message m = new Message(from, to, subject, message);
                WriteMessageActivity.getMessageService().sendMessage(m, getActivity());
            }
        });

        return v;
    }
}
