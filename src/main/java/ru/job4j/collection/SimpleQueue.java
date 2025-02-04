package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private T value;
    private int indexPush;
    private int indexPoll;

    public T poll() {
        if (indexPush == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        while (indexPoll != indexPush) {
            output.push(input.pop());
            indexPoll++;
        }
        value = output.pop();
        indexPoll--;
        indexPush--;
        while (indexPoll > 0) {
            input.push(output.pop());
            indexPoll--;
        }
        return value;
    }

    public void push(T value) {
        input.push(value);
        indexPush++;
    }
}