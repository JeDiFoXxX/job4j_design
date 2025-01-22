package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return findEven(this.index) >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.index = findEven(this.index);
        return data[this.index++];
    }

    private int findEven(int start) {
        for (int index = start; index < data.length; index++) {
            if (data[index] % 2 == 0) {
                return index;
            }
        }
        return -1;
    }
}