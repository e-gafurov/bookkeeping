package com.ris.egafurov.bookkeeping;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ModelReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModelReportFragment extends Fragment {

    private List<ModelBase> mReportModelList;
    private ReportModel mReportModel;

    public ModelReportFragment() {
        // Required empty public constructor
        mReportModelList = ModelLab.get(getContext()).getModels();
        mReportModel = new ReportModel(mReportModelList);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ModelReportFragment.
     */
    public static ModelReportFragment newInstance() {
        ModelReportFragment fragment = new ModelReportFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model_report, container, false);
    }

}
