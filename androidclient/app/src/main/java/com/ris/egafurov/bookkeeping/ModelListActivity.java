package com.ris.egafurov.bookkeeping;

import android.support.v4.app.Fragment;

public class ModelListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ModelListFragment();
    }
}
