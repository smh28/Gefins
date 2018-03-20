package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import is.hi.teymi9.gefins.model.User;

/**
 * Fragment fyrir viðmótið í AdminActiv og virknina þar.
 *
 * @author Ólöf Fríða
 * @version 1.0
 */

public class AdminFragment extends Fragment {

    List<User> users;
    // Takki fyrir útskráningu
    private Button mLogout;
    // Listi fyrir notendur
    private ListView mUserList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setUsersiteActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adminsite, container, false);

        mLogout = (Button) v.findViewById(R.id.logout_button);
        mUserList = (ListView) v.findViewById(R.id.userList);
        users = AdminActivity.getUserService().getAllUsers();
        int count = users.size();
        if(!users.isEmpty()){

            String[] userName = new String[count];
            int i = 0;
            for(User u: users){
                userName[i] = u.getUsername();
                i++;
            }

            ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    userName
            );

            mUserList.setAdapter(listViewAdapter);
        }


        mLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LoginActivity.getUserService().logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
