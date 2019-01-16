package com.ris.egafurov.bookkeeping;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

public class IncomeCursorWrapper extends CursorWrapper {
    public IncomeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Income getIncome(){
        String uuidString = getString(getColumnIndex(ModelDbSchema.IncomeTable.Cols.UUID));
        long date = getLong(getColumnIndex(ModelDbSchema.IncomeTable.Cols.EVENTDATE));
        double sum = getDouble(getColumnIndex(ModelDbSchema.IncomeTable.Cols.SUM));
        String typeIncome = getString(getColumnIndex(ModelDbSchema.IncomeTable.Cols.TYPEINCOME));
        Income income = new Income(UUID.fromString(uuidString));
        income.setDate(new Date(date));
        income.setSum(sum);
        income.setTypeIncome(typeIncome);
        return income;

    }
}
