package is.hi.teymi9.gefins;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    private List<User> users;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.getUserService().setUsersiteActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_delete_user, container, false);

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

        return v;
    }

}
