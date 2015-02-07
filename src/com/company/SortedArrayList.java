package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by andreperkins on 2/6/15.
 */
public class SortedArrayList<T> extends ArrayList<T>  {
    final private Comparator<T> comparator;

    public SortedArrayList(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add(T object) {
        int index = Collections.binarySearch(this, object, comparator);
        if (index < 0) index = ~index;
        super.add(index, object);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean result = super.addAll(collection);
        Collections.sort(this, comparator);
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        boolean result = super.addAll(index, collection);
        Collections.sort(this, comparator);
        return result;
    }
}