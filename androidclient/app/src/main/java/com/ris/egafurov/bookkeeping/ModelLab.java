package com.ris.egafurov.bookkeeping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ModelLab {
    public static final ModelLab get(Context context) {
        if (sModelLab == null)
        {
            sModelLab = new ModelLab(context);
        }
        return sModelLab;
    }

    private static ModelLab sModelLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private ModelLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ModelBaseHelper(mContext).getWritableDatabase();
    }

    private ContentValues getContentValuesByIncome(Income income) {
        ContentValues values = new ContentValues();
        values.put(ModelDbSchema.IncomeTable.Cols.UUID, income.getId().toString());
        values.put(ModelDbSchema.IncomeTable.Cols.EVENTDATE, income.getDate().getTime());
        values.put(ModelDbSchema.IncomeTable.Cols.SUM, income.getSum());
        values.put(ModelDbSchema.IncomeTable.Cols.TYPEINCOME, income.getTypeIncome());
        return values;
    }

    private ContentValues getContentValuesByExpense(Expense expense) {
        ContentValues values = new ContentValues();
        values.put(ModelDbSchema.ExpenseTable.Cols.UUID, expense.getId().toString());
        values.put(ModelDbSchema.ExpenseTable.Cols.EVENTDATE, expense.getDate().getTime());
        values.put(ModelDbSchema.ExpenseTable.Cols.SUM, expense.getSum());
        values.put(ModelDbSchema.ExpenseTable.Cols.TYPEEXPENSE, expense.getTypeExpense());
        values.put(ModelDbSchema.ExpenseTable.Cols.NAMESHOP, expense.getNameShop());
        return values;
    }

    public void AddIncome(Income income)
    {
        ContentValues values = getContentValuesByIncome(income);
        mDatabase.insert(ModelDbSchema.IncomeTable.NAME, null, values);
    }

    public void AddExpense(Expense expense)
    {
        ContentValues values = getContentValuesByExpense(expense);
        mDatabase.insert(ModelDbSchema.ExpenseTable.NAME, null, values);
    }

    public List<ModelBase> getModels()
    {
        List<ModelBase> models = new ArrayList<>();
        IncomeCursorWrapper cursorIncome = queryIncomes(null, null);
        try {
            cursorIncome.moveToFirst();
            while (!cursorIncome.isAfterLast()) {
                models.add(cursorIncome.getIncome());
                cursorIncome.moveToNext();
            }
        } finally {
            cursorIncome.close();
        }

        ExpenseCursorWrapper cursorExpense = queryExpense(null, null);
        try {
            cursorExpense.moveToFirst();
            while (!cursorExpense.isAfterLast()) {
                models.add(cursorExpense.getExpense());
                cursorExpense.moveToNext();
            }
        } finally {
            cursorExpense.close();
        }

        models.sort(new ComparatorModeBase());

        return models;
    }

    public Income getIncome(UUID id)
    {
        IncomeCursorWrapper cursor = queryIncomes(
                ModelDbSchema.IncomeTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getIncome();
        } finally {
            cursor.close();
        }

    }

    public Expense getExpense(UUID id){
        ExpenseCursorWrapper cursor = queryExpense(
                ModelDbSchema.ExpenseTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getExpense();
        } finally {
            cursor.close();
        }
    }

    public void updateIncome(Income income) {
        String uuidString = income.getId().toString();
        ContentValues values = getContentValuesByIncome(income);
        mDatabase.update(ModelDbSchema.IncomeTable.NAME, values,
                ModelDbSchema.IncomeTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    public void updateExpense(Expense expense) {
        String uuidString = expense.getId().toString();
        ContentValues values = getContentValuesByExpense(expense);
        mDatabase.update(ModelDbSchema.ExpenseTable.NAME, values,
                ModelDbSchema.ExpenseTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    public void deleteIncome(Income income){
        String uuidString = income.getId().toString();
        mDatabase.delete(ModelDbSchema.IncomeTable.NAME,
                ModelDbSchema.IncomeTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    public void deleteExpense(Expense expense){
        String uuidString = expense.getId().toString();
        mDatabase.delete(ModelDbSchema.ExpenseTable.NAME,
                ModelDbSchema.ExpenseTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private IncomeCursorWrapper queryIncomes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(ModelDbSchema.IncomeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
                );
        return new IncomeCursorWrapper(cursor);
    }

    private ExpenseCursorWrapper queryExpense(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(ModelDbSchema.ExpenseTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new ExpenseCursorWrapper(cursor);
    }

}
