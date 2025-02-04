package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> l = head;
        final Node<T> newNode = new Node<>(value, null);
        if (l == null) {
            head = newNode;
        } else {
            while (l.next != null) {
                l = l.next;
            }
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> element = head;
        for (int i = 0; i != index; i++) {
            element = element.next;
        }
        return element.item;
    }

    public T deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        final T value = head.item;
        final Node<T> newNode = head.next;
        head.item = null;
        head.next = null;
        head = newNode;
        size--;
        modCount++;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            Node<T> start = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return start != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = start.item;
                start = start.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}