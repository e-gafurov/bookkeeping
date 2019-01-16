package com.ris.egafurov.bookkeeping;

import java.util.UUID;

public class Income extends ModelBase {

    public String getTypeIncome() {
        return mTypeIncome;
    }

    public void setTypeIncome(String typeIncome) {
        mTypeIncome = typeIncome;
    }

    private String mTypeIncome;

    public Income() {
        super(true);
    }

    public Income(UUID id){
        super(id, true);
    }
}
