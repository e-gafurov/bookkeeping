package com.ris.egafurov.bookkeeping;

import java.util.Date;
import java.util.UUID;

public abstract class ModelBase {
    private UUID mId;
    private Date mDate;
    private double mSum;
    private boolean mIsIncome;

    public UUID getId() {
        return mId;
    }

    public boolean isIncome() {
        return mIsIncome;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public double getSum() {
        return mSum;
    }

    public void setSum(double sum) {
        mSum = sum;
    }


    protected ModelBase(boolean isIncome)
    {
        mIsIncome = isIncome;
    }

    protected ModelBase(UUID id, boolean isIncome)
    {
        this(isIncome);
        mId = id;
    }
}
