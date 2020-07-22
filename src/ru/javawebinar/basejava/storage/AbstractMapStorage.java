package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage<SK> extends AbstractStorage<SK> {
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
    protected List<Resume> performGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected void performSave(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void performUpdate(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }
}
