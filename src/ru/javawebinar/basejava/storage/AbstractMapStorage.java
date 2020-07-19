package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public abstract class AbstractMapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<>(storage.values());
        result.sort(Comparator.comparing(Resume::getFullName));
        return result;
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
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void performDelete(String uuid, Object key) {
        storage.remove(uuid);
    }
}
