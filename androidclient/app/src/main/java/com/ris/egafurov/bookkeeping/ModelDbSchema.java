package com.ris.egafurov.bookkeeping;

public class ModelDbSchema {
    public  static class ShareCols {
        public static String UUID = "uuid";
        public  static  String EVENTDATE = "eventdate";
        public static String SUM = "sum";
    }

    public static final class IncomeTable {
        public static final String NAME= "incomes";

        public static final class Cols extends ShareCols {
            public static final String TYPEINCOME = "typeincome";
        }
    }

    public static final class ExpenseTable {
        public static final String NAME= "expenses";

        public static final class Cols extends ShareCols {
            public static final String TYPEEXPENSE = "typeexpense";
            public static final String NAMESHOP = "nameshop";
        }
    }
}
