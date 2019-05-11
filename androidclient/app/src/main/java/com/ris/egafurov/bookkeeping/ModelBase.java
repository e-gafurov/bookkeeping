package com.ris.egafurov.bookkeeping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
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
        return mDate == null ? new Date() : mDate;
    }

    public String getDateString() {return Utils.dateToString(getDate());}

    public void setDate(Date date) {
        mDate = date;
    }

    public double getSum() {
        return mSum;
    }

    public String getSumString() { return String.format(CurrentLocale.GetCurentLocale(),"%,.2f", mSum);}

    public void setSum(double sum) {
        mSum = sum;
    }


    protected ModelBase(boolean isIncome)
    {
        mId = UUID.randomUUID();
        mIsIncome = isIncome;
    }

    protected ModelBase(UUID id, boolean isIncome)
    {
        this(isIncome);
        mId = id;
    }
}
