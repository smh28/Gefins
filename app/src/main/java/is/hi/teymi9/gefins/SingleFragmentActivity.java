package is.hi.teymi9.gefins;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Yfirklasi fyrir activities til þess að endurnýta kóða sem þarf til þess að búa til nýtt Fragment
 *
 * @author Einar
 * @version 1.0
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    /**
     * Skilar réttu Fragmenti sem búa skal til
     * @return Fragmentið sem búa skal til
     */
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
