package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        int index = getIndex(key);
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = getIndex(key);
        if (table[index] != null && isKeyMatch(index, key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = getIndex(key);
        if (table[index] != null && isKeyMatch(index, key)) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index = 0;
            int rsl = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[rsl] == null) {
                    rsl++;
                }
                index++;
                return table[rsl++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if (count >= (int) (capacity * LOAD_FACTOR)) {
            capacity *= 2;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (MapEntry<K, V> entry : table) {
                if (entry != null) {
                    newTable[getIndex(entry.key)] = entry;
                }
            }
            table = newTable;
        }
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean isKeyMatch(int index, K key) {
        return table[index] != null && (Objects.hashCode(table[index].key) == Objects.hashCode(key)
                && Objects.equals(table[index].key, key));
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
