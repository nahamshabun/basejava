package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {
    private final HashMap<String, Resume> storage = new HashMap<>();

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
    protected boolean has(String uuid) {
        return storage.get(uuid) != null;
    }

    @Override
    protected void performSave(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume performGet(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void performUpdate(Resume resume, String uuid) {
        storage.put(uuid, resume);
    }

    @Override
    protected void performDelete(String uuid) {
        storage.remove(uuid);
    }
}
