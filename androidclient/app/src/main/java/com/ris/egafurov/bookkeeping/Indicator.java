package com.ris.egafurov.bookkeeping;

public class Indicator {
    private String mName;
    private int mPercent;

    public Indicator(String name, int percent)
    {
        mName = name;
        mPercent = percent;
    }

    public String getName()
    {
        return mName;
    }

    public int getPercent()
    {
        return mPercent;
    }
}
