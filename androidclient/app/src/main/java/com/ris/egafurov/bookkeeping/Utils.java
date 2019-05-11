package com.ris.egafurov.bookkeeping;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

public final class Utils {
    public static double parseDouble(String source)
    {
        try {
            NumberFormat format = NumberFormat.getInstance(CurrentLocale.GetCurentLocale());
            Number number = format.parse(source);
            return number.doubleValue();
        }catch (Exception ex)
        {
            return 0.0d;
        }

    }

    public static String dateToString(Date source)
    {
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, CurrentLocale.GetCurentLocale());
        return format.format(source);
    }
}
