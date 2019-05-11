package com.ris.egafurov.bookkeeping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

public class IncomeFragment extends Fragment {
    private static final String ARG_INCOME_ID = "com.ris.egafurov.bookkeeping.arg_income_id";
    private static final String DIALOG_DATE = "com.ris.egafurov.bookkeeping.DialogDateIncome";
    private static final int REQUEST_DATE = 0;


    private UUID mId;
    private Income mIncome;
    private EditText mSumField;
    private Button mDateField;
    private EditText mTypeField;
    private Button mSaveBtn;

    public IncomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id - Id models.
     * @return A new instance of fragment IncomeFragment.
     */
    public static IncomeFragment newInstance(UUID id) {
        IncomeFragment fragment = new IncomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_INCOME_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = (UUID)getArguments().getSerializable(ARG_INCOME_ID);
        }
        mIncome = ModelLab.get(getActivity()).getIncome(mId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_income, container, false);
        mSumField = (EditText)v.findViewById(R.id.income_sum);
        mSumField.setText(mIncome.getSumString());
        mDateField = (Button) v.findViewById(R.id.income_date);
        updateDate();
        mDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mIncome.getDate());
                dialog.setTargetFragment(IncomeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        mTypeField = (EditText)v.findViewById(R.id.income_type);
        mTypeField.setText(mIncome.getTypeIncome());
        mSaveBtn = (Button)v.findViewById(R.id.income_save);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsValidate()) {
                    mIncome.setSum(Utils.parseDouble(mSumField.getText().toString()));
                    mIncome.setTypeIncome(mTypeField.getText().toString());
                    ModelLab.get(getActivity()).updateIncome(mIncome);
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
        if (TextUtils.isEmpty(date)){
            mDateField.setError(requiredMsg);
            result &= false;
        }

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
        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mIncome.setDate(date);
            updateDate();
        }

    }

    private void updateDate() {
        mDateField.setText(mIncome.getDateString());
    }
}
