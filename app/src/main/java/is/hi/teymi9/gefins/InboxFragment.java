package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
    //Þjónusta fyrir notanda

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inbox, container, false);   //var R.layout.fragment_display_ads
        ListView lv = (ListView) v.findViewById(R.id.listOfMessages);      //var listOfAds

        mBack = (Button) v.findViewById(R.id.inboxTilbaka);
        allMessages = WriteMessageActivity.getMessageService().getMessages();

        int countMessages = allMessages.size();
        System.out.println("allMessages stærð: " + countMessages);

        //Býr til tóm strengjafylki að sömu stærð og fjöldi auglýsinga
        String[] messageSubject = new String[countMessages];
        String[] messageAuthor = new String[countMessages];
        String[] messageDate = new String[countMessages];
        String[] messageContent = new String[countMessages];
        String[] messageSummary = new String[countMessages];

        int i = 0;
        for(Message m: allMessages) {
            messageSubject[i] = m.getSubject();
            messageAuthor[i] = m.getSender();
            messageDate[i] = m.getDate();
            messageContent[i] = m.getMessage();
            messageSummary[i] = m.getSubject() + "\n" + m.getSender() + "\n " + m.getDate();

            i++;
        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,   //var: android.R.layout.simple_list_item_1   /  R.layout.fragment_list_of_ads
                messageSummary
        );

        lv.setAdapter(listViewAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(getActivity(), DisplaySingleAdActivity.class);
                intent.putExtra("name", adName[position]);
                intent.putExtra("giveOrTake", adGiveOrTake[position]);
                intent.putExtra("type", adType[position]);
                intent.putExtra("typeOfTye", adTypeOfType[position]);
                intent.putExtra("color", adColor[position]);
                intent.putExtra("description", adDescription[position]);
                intent.putExtra("location", adLocation[position]);
                intent.putExtra("username", adUsername[position]);

                startActivity(intent);*/
                String messagePicked = "Þú valdir auglýsinguna " +
                        String.valueOf(lv.getItemAtPosition(position));
                Toast.makeText(getActivity(), messagePicked, Toast.LENGTH_SHORT).show();
            }
        });

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
}

