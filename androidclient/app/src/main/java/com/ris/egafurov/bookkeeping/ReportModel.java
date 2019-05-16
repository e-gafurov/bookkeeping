package com.ris.egafurov.bookkeeping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ReportModel {

    private List<ModelBase> mModels;

    public ReportModel(List<ModelBase> models)
    {
        mModels = models;
    }

    public List<Indicator> GetCurrentMonth()
    {
        List<Indicator> result = new ArrayList<Indicator>();

        return result;
    }

    public List<Indicator> GetPreviewMonth()
    {
        List<Indicator> result = new ArrayList<Indicator>();
        return result;
    }

    public List<Indicator> GetCurrentYear()
    {
        List<Indicator> result = new ArrayList<Indicator>();
        return result;
    }
}
