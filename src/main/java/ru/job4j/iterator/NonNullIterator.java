package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {
    private Integer[] data;
    private int index;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return findNull(this.index) >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.index = findNull(this.index);
        return data[index++];
    }

    private int findNull(int start) {
        for (int index = start; index < data.length; index++) {
            if (data[index] != null) {
                return index;
            }
        }
        return -1;
    }

}