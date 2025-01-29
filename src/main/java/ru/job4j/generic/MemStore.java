package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (model.getId() != null) {
            storage.putIfAbsent(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        return storage.entrySet().stream()
                .filter(element -> element.getKey().equals(id))
                .findFirst()
                .map(element -> {
                    storage.put(id, model);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public T findById(String id) {
        return storage.entrySet().stream()
                .filter(element -> element.getKey().equals(id))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }
}