package com.ris.egafurov.bookkeeping;

import java.util.UUID;

public class Expense extends ModelBase {

    public int getTypeExpense() {
            return mTypeExpense;
    }

    public void setTypeExpense(int typeExpense) {
        mTypeExpense = typeExpense;
    }

    public String getNameShop() {
        return mNameShop;
    }

    public void setNameShop(String nameShop) {
        mNameShop = nameShop;
    }

    private int mTypeExpense;
    private String mNameShop;

    public Expense() {
        super(false);
    }

    public Expense(UUID id) {
        super(id, false);
    }
}
