package com.ris.egafurov.bookkeeping;

import java.text.NumberFormat;

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
}
