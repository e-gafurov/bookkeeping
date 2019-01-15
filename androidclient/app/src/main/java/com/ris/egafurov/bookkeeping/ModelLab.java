package com.ris.egafurov.bookkeeping;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ModelLab {
    public static final ModelLab getModelLab() {
        if (sModelLab == null)
        {
            sModelLab = new ModelLab();
        }
        return sModelLab;
    }

    private static ModelLab sModelLab;

    public ModelLab get(Context context)
    {
        return null;
    }

    public void AddIncome(Income income)
    {

    }

    public void AddExpense(Expense expense)
    {

    }

    public List<BaseModel> getModels()
    {
        return new ArrayList<>();
    }

    public BaseModel getModel(UUID id)
    {
        return null;
    }


}
