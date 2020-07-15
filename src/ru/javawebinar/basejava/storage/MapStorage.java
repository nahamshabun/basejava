package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean contains(Object key) {
        return storage.get(key) != null;
    }

    @Override
    protected void performSave(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume performGet(String uuid, Object key) {
        return storage.get(uuid);
    }

    @Override
    protected void performUpdate(Resume resume, Object key) {
        storage.put(key.toString(), resume);
    }

    @Override
    protected void performDelete(String uuid, Object key) {
        storage.remove(uuid);
    }
}
