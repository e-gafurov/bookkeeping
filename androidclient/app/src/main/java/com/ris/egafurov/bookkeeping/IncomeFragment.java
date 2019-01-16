package com.ris.egafurov.bookkeeping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IncomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IncomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INCOME_ID = "com.ris.egafurov.bookkeeping.arg_income_id";

    // TODO: Rename and change types of parameters
    private String mId;
    private Income mIncome;
    private EditText mSumField;
    private Button mIncomeDateField;
    private EditText mTypeFiled;
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
    // TODO: Rename and change types and number of parameters
    public static IncomeFragment newInstance(String id) {
        IncomeFragment fragment = new IncomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_INCOME_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getString(ARG_INCOME_ID);
        }
        mIncome = new Income(UUID.fromString(mId));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_income, container, false);
        mSumField = (EditText)v.findViewById(R.id.income_sum);
        mIncomeDateField = (Button) v.findViewById(R.id.income_date);
        mTypeFiled = (EditText)v.findViewById(R.id.income_type);
        mSaveBtn = (Button)v.findViewById(R.id.income_save);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsValidate()) {
                    //mIncome.setDate(new Date());
                    mIncome.setSum(Double.parseDouble(mSumField.getText().toString()));
                    mIncome.setTypeIncome(mTypeFiled.getText().toString());
                    ModelLab.get(getActivity()).updateIncome(mIncome);
                    //getActivity().onBackPressed();
                }
            }
        });
        return v;
    }

    private boolean IsValidate(){
        boolean result = true;
        String date = mIncomeDateField.getText().toString();
        String requiredMsg = getResources().getString(R.string.required_field);
        /*if (TextUtils.isEmpty(date)){
            mIncomeDateField.setError(requiredMsg);
            result &= false;
        }*/

        String sum = mSumField.getText().toString();

        if (TextUtils.isEmpty(sum)){
            mSumField.setError(requiredMsg);
            result &= false;
        }else if (Double.parseDouble(sum) <= 0) {
            mSumField.setError(getResources().getString(R.string.error_sum_less_zero));
            result &= false;
        }

        String type = mTypeFiled.getText().toString();
        if (TextUtils.isEmpty(type)){
            mTypeFiled.setError(requiredMsg);
            result &= false;
        }
        return result;
    }

}
