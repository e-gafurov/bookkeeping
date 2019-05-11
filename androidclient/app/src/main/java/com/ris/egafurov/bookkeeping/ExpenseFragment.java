package com.ris.egafurov.bookkeeping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

public class ExpenseFragment extends Fragment {
    private static final String ARG_EXPENSE_ID = "com.ris.egafurov.bookkeeping.arg_expense_id";
    private static final String DIALOG_DATE = "com.ris.egafurov.bookkeeping.DialogDateExpense";
    private static final int REQUEST_DATE = 0;


    private UUID mId;
    private Expense mExpense;
    private EditText mSumField;
    private Button mDateField;
    private EditText mTypeField;
    private EditText mShopField;
    private Button mSaveBtn;

    public ExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id - Id models.
     * @return A new instance of fragment IncomeFragment.
     */
    public static ExpenseFragment newInstance(UUID id) {
        ExpenseFragment fragment = new ExpenseFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_EXPENSE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = (UUID)getArguments().getSerializable(ARG_EXPENSE_ID);
        }
        mExpense = ModelLab.get(getActivity()).getExpense(mId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_expense, container, false);
        mSumField = (EditText)v.findViewById(R.id.expense_sum);
        mSumField.setText(mExpense.getSumString());
        mDateField = (Button) v.findViewById(R.id.expense_date);
        updateDate();
        mDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mExpense.getDate());
                dialog.setTargetFragment(ExpenseFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        mTypeField = (EditText)v.findViewById(R.id.expense_type);
        mTypeField.setText(mExpense.getTypeExpense());
        mShopField = (EditText)v.findViewById(R.id.expense_shop);
        mShopField.setText(mExpense.getNameShop());
        mSaveBtn = (Button)v.findViewById(R.id.expense_save);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsValidate()) {
                    mExpense.setSum(Utils.parseDouble(mSumField.getText().toString()));
                    mExpense.setTypeExpense(mTypeField.getText().toString());
                    mExpense.setNameShop(mShopField.getText().toString());
                    ModelLab.get(getActivity()).updateExpense(mExpense);
                    getActivity().onBackPressed();
                }
            }
        });
        return v;
    }

    private boolean IsValidate(){
        boolean result = true;
        String date = mDateField.getText().toString();
        String requiredMsg = getResources().getString(R.string.required_field);
        /*if (TextUtils.isEmpty(date)){
            mIncomeDateField.setError(requiredMsg);
            result &= false;
        }*/

        String sum = mSumField.getText().toString();

        if (TextUtils.isEmpty(sum)){
            mSumField.setError(requiredMsg);
            result &= false;
        }else if (Utils.parseDouble(sum) <= 0) {
            mSumField.setError(getResources().getString(R.string.error_sum_less_zero));
            result &= false;
        }

        String type = mTypeField.getText().toString();
        if (TextUtils.isEmpty(type)){
            mTypeField.setError(requiredMsg);
            result &= false;
        }

        String shop = mShopField.getText().toString();
        if (TextUtils.isEmpty(shop)){
            mShopField.setError(requiredMsg);
            result &= false;
        }
        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mExpense.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateField.setText(mExpense.getDateString());
    }
}
