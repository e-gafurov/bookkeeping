package com.ris.egafurov.bookkeeping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ModelListFragment extends Fragment {

    private RecyclerView mModelRecyclerView;
    private TextView mCurrentBalanceTextView;
    private ModelAdapter mAdapterModelList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_model_list, container, false);
        mModelRecyclerView = (RecyclerView) v.findViewById(R.id.model_recycle_view);
        mModelRecyclerView.setHasFixedSize(true);
        mModelRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCurrentBalanceTextView = (TextView) v.findViewById(R.id.model_current_balance);
        UpdateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        UpdateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_model_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_expense:
                Expense expense = new Expense();
                ModelLab.get(getActivity()).AddExpense(expense);
                Intent intentExpense = MainActivity.newIntent(getActivity(), expense.getId(), expense.isIncome());
                startActivity(intentExpense);
                Log.d("Menu", "Add Expense");
                return true;
            case R.id.new_income:
                Income income = new Income();
                ModelLab.get(getActivity()).AddIncome(income);
                Intent intentIncome = MainActivity.newIntent(getActivity(), income.getId(), income.isIncome());
                startActivity(intentIncome);
                Log.d("Menu", "Add Income");
                return true;
            case  R.id.new_expense_qr:
                ServiceOFD serviceOFD = new ServiceOFD(getActivity(),"9288000100035206",  "54802",  "1977932697");
                serviceOFD.GetExpense();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void SetCurrentBalance(double balance)
    {
        mCurrentBalanceTextView.setText(String.format("%.2f", balance));
        mCurrentBalanceTextView.setTextColor(ContextCompat.getColor(getActivity(), balance > 0 ? R.color.colorGreenText : R.color.colorRedText));
    }

    private void UpdateUI(){
        ModelLab modelLab = ModelLab.get(getActivity());
        List<ModelBase> list = modelLab.getModels();
        double currentBalance = ModelLab.CalcBalance(list);
        SetCurrentBalance(currentBalance);

        if (mAdapterModelList == null) {
            mAdapterModelList = new ModelAdapter(list);
            mModelRecyclerView.setAdapter(mAdapterModelList);
       }else {
            mAdapterModelList.setModels(list);
            mAdapterModelList.notifyDataSetChanged();
        }
    }

    private class ModelHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener  {

        private TextView mDateField;
        private TextView mSumField;
        private ModelBase mModelBase;

        public ModelHolder(LayoutInflater layoutInflater, ViewGroup parent) {
            super(layoutInflater.inflate(R.layout.list_item_model, parent, false));
            itemView.setOnClickListener(this);

            mDateField = (TextView)itemView.findViewById(R.id.model_date);
            mSumField = (TextView)itemView.findViewById(R.id.model_sum);
        }

        public void bind(ModelBase modelBase){
            mModelBase = modelBase;
            mDateField.setText(mModelBase.getDate().toString());
            mSumField.setText(mModelBase.getSumString());
            mSumField.setTextColor(ContextCompat.getColor(getActivity(), mModelBase.isIncome() ? R.color.colorGreenText : R.color.colorRedText));
        }

        @Override
        public void onClick(View v) {
            Intent intent = MainActivity.newIntent(getActivity(), mModelBase.getId(), mModelBase.isIncome());
            startActivity(intent);

        }
    }

    private class ModelAdapter extends RecyclerView.Adapter<ModelHolder> {

        private List<ModelBase> mModels;

        public ModelAdapter(List<ModelBase> models) {
            mModels = models;
        }

        @NonNull
        @Override
        public ModelHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ModelHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull ModelHolder modelHolder, int i) {
            ModelBase modelBase = mModels.get(i);
            modelHolder.bind(modelBase);
        }

        @Override
        public int getItemCount() {
            return mModels.size();
        }

        public void setModels(List<ModelBase> models) {
            mModels = models;
        }
    }
}
