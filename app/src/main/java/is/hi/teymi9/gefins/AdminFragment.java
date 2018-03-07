package is.hi.teymi9.gefins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment fyrir viðmótið í AdminActiv og virknina þar.
 *
 * @author Ólöf Fríða
 * @version 1.0
 */

public class AdminFragment extends Fragment {

    // Takki fyrir útskráningu
    private Button mLogout;
    //

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
