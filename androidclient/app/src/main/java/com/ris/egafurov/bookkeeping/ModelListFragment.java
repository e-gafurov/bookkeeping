package com.ris.egafurov.bookkeeping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ModelListFragment extends Fragment {

    private RecyclerView mModelRecyclerView;
    private ModelAdapter mAdapterModelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_model_list, container, false);
        mModelRecyclerView = (RecyclerView) v.findViewById(R.id.model_recycle_view);
        mModelRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        UpdateUI();
    }

    private void UpdateUI(){
        ModelLab modelLab = ModelLab.get(getActivity());
        List<ModelBase> list = modelLab.getModels();
        if (mAdapterModelList == null) {
            mAdapterModelList = new ModelAdapter(list);
            mModelRecyclerView.setAdapter(mAdapterModelList);
        }else {
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
    }
}
