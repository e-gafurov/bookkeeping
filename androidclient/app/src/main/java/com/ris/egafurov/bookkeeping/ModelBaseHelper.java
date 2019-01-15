package com.ris.egafurov.bookkeeping;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ModelBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASENAME = "bookkeeping.db";

    public ModelBaseHelper(Context context){
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ModelDbSchema.IncomeTable.NAME + " (" +
                " _id integer primary key autoincrement, " +
                ModelDbSchema.IncomeTable.Cols.UUID + ", " +
                ModelDbSchema.IncomeTable.Cols.EVENTDATE + ", " +
                ModelDbSchema.IncomeTable.Cols.SUM + ", " +
                ModelDbSchema.IncomeTable.Cols.TYPEINCOME +
                ")"
        );
        db.execSQL("create table " + ModelDbSchema.ExpenseTable.NAME + " (" +
                " _id integer primary key autoincrement, " +
                ModelDbSchema.ExpenseTable.Cols.UUID + ", " +
                ModelDbSchema.ExpenseTable.Cols.EVENTDATE + ", " +
                ModelDbSchema.ExpenseTable.Cols.SUM + ", " +
                ModelDbSchema.ExpenseTable.Cols.TYPEEXPENSE + ", " +
                ModelDbSchema.ExpenseTable.Cols.NAMESHOP +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
