package com.ris.egafurov.bookkeeping;

import java.util.Comparator;

public class ComparatorModeBase implements Comparator<ModelBase> {

    @Override
    public int compare(ModelBase o1, ModelBase o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}
