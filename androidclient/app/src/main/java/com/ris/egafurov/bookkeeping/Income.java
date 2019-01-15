package com.ris.egafurov.bookkeeping;

import java.util.UUID;

public class Income extends BaseModel {

    public int getTypeIncome() {
        return mTypeIncome;
    }

    public void setTypeIncome(int typeIncome) {
        mTypeIncome = typeIncome;
    }

    private int mTypeIncome;

    public Income() {
        super(true);
    }

    public Income(UUID id){
        super(id, true);
    }
}
