package is.hi.teymi9.gefins;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Einar on 25.2.2018.
 */

public class EditUserActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new EditUserFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, EditUserActivity.class);
        return intent;
    }

}
