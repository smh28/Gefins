package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import is.hi.teymi9.gefins.model.User;


/**
 * Fragment fyrir viðmótið í AdminDeleteUserActivity og virknina þar.
 *
 * @author Kristín María
 * @version 1.0
 */

public class AdminDeleteUserFragment extends Fragment {

    private ListView mUserList;
    private List<User> allUsers;
    //Tilbaka takki
    private Button mBack;
    //Takki til að eyða auglýsingum
    private Button mDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setUsersiteActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_delete_user, container, false);
        mBack = (Button) v.findViewById(R.id.back_button2);
        mDelete = (Button) v.findViewById(R.id.delete_ad_button3);
        mUserList = (ListView) v.findViewById(R.id.userList);


        allUsers = AdminActivity.getUserService().getAllUsers();
        int countUsers = allUsers.size();

        //Býr til tóm strengjafylki að sömu stærð og fjöldi notenda
        String[] username = new String[countUsers];
        String[] fullName = new String[countUsers];
        String[] email = new String[countUsers];
        String[] phonenr = new String[countUsers];
        Integer[] zip = new Integer[countUsers];
        String[] address = new String[countUsers];
        Boolean[] hasadminauthority = new Boolean[countUsers];

        int i = 0;
        for(User a: allUsers) {
            //Fylla inn í strengjafylkin
            username[i] = a.getUsername();
            fullName[i] = a.getFullName();
            email[i] = a.getEmail();
            phonenr[i] = a.getPhonenr();
            zip[i] = a.getZip();
            address[i] = a.getAddress();
            hasadminauthority[i] = a.isHasadminauthority();
            i++;
        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_multiple_choice,
                username
        );

        mUserList.setAdapter(listViewAdapter);


        //Sendir admin tilbaka á admin síðu
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User currentUser = LoginActivity.getUserService().getCurrentUser();
                System.out.println("DisplayAdsFragment í back hnappi: currentUser er " + currentUser);
                Intent intent = new Intent(getActivity(), AdminActivity.class);
                startActivity(intent);
            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checked = mUserList.getCheckedItemPositions();
                int size = checked.size(); // number of name-value pairs in the array
                for (int i = 0; i < size; i++) {
                    int key = checked.keyAt(i);
                    boolean value = checked.get(key);
                    if (value)
                        System.out.println(key);
                    String tag = mUserList.getItemAtPosition(key).toString();
                    System.out.println(tag);
                    AdminActivity.getAdService().deleteAdByName(tag, getActivity());
                }
            }
        });

        return v;
    }

}
