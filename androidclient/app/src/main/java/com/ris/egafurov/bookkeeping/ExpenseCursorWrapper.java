package com.ris.egafurov.bookkeeping;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.view.Display;

import java.util.Date;
import java.util.UUID;

public class ExpenseCursorWrapper extends CursorWrapper {
    public ExpenseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Expense getExpense() {
        String uuidString = getString(getColumnIndex(ModelDbSchema.ExpenseTable.Cols.UUID));
        long date = getLong(getColumnIndex(ModelDbSchema.ExpenseTable.Cols.EVENTDATE));
        double sum = getDouble(getColumnIndex(ModelDbSchema.ExpenseTable.Cols.SUM));
        int typeExpense = getInt(getColumnIndex(ModelDbSchema.ExpenseTable.Cols.TYPEEXPENSE));
        String nameShop = getString(getColumnIndex(ModelDbSchema.ExpenseTable.Cols.NAMESHOP));
        Expense expense = new Expense(UUID.fromString(uuidString));
        expense.setDate(new Date(date));
        expense.setSum(sum);
        expense.setTypeExpense(typeExpense);
        expense.setNameShop(nameShop);
        return expense;
    }
}
