package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.hi.teymi9.gefins.model.Message;

/**
 * Fragment fyrir viðmótið í WriteMessageActivity og virknina þar.
 *
 * @author Einar
 * @version 1.0
 * @date
 */

public class WriteMessageFragment extends Fragment {

    // Takki til að senda skilaboð
    private Button mSend;
    // Takki vil að hætta við
    private Button mCancel;
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
        mCancel = (Button) v.findViewById(R.id.cancel);

        Bundle bundle = getActivity().getIntent().getExtras();

        if(bundle != null) {
            String recipient = bundle.getString("recipient");
            mTo.setText(recipient);
        }

        mSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String from = LoginActivity.getUserService().getCurrentUser().getUsername();
                String to = mTo.getText().toString();
                String subject = mSubject.getText().toString();
                String message = mMessage.getText().toString();
                Message m = new Message(from, to, subject, message);
                if(to.length() < 4) {
                    Toast.makeText(getActivity(), R.string.ekki_gilt_notandanafn, Toast.LENGTH_LONG).show();
                }
                else if(subject.length() == 0) {
                    Toast.makeText(getActivity(), R.string.titill_tomur, Toast.LENGTH_LONG).show();
                }
                else if(message.length() == 0) {
                    Toast.makeText(getActivity(), R.string.skilabod_tom, Toast.LENGTH_LONG).show();
                }
                else {
                    WriteMessageActivity.getMessageService().sendMessage(m, getActivity());
                }
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return v;
    }
}
