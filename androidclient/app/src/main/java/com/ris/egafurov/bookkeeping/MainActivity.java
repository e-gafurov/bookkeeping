package com.ris.egafurov.bookkeeping;

import android.arch.lifecycle.ReportFragment;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    private static final String EXTRA_FRAGMENT_ID = "com.ris.egafurov.bookkeeping.extra_fragment_id";
    private static final String EXTRA_MODEL_ID = "com.ris.egafurov.bookkeeping.extra_model_id";
    private static final String EXTRA_ISINCOME = "com.ris.egafurov.bookkeeping.extra_isincome";

    public static Intent newIntent(Context packageContext, String fragmentId)
    {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_FRAGMENT_ID, fragmentId);
        return intent;
    }

    public static Intent newIntent(Context packageContext, String fragmentId,  UUID modelId, boolean isIncome) {
        Intent intent = MainActivity.newIntent(packageContext, fragmentId);
        intent.putExtra(EXTRA_MODEL_ID, modelId);
        intent.putExtra(EXTRA_ISINCOME, isIncome);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        String fragmentId = (String) getIntent().getSerializableExtra(EXTRA_FRAGMENT_ID);
        switch (fragmentId) {
            case FragmentName.Expense:
            case FragmentName.Income:
                UUID modelId = (UUID) getIntent()
                        .getSerializableExtra(EXTRA_MODEL_ID);
                boolean isIncome = (boolean) getIntent()
                        .getSerializableExtra(EXTRA_ISINCOME);
                if (isIncome) {
                    return IncomeFragment.newInstance(modelId);
                } else {
                    return ExpenseFragment.newInstance(modelId);
                }
            case FragmentName.Report:
                return ModelReportFragment.newInstance();

            default:
                return new Fragment();
        }
    }
}
