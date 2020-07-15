package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

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
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected boolean contains(Object index) {
            return (Integer) index >= 0;
    }

    @Override
    protected void performSave(Resume resume, Object index) {
        storage.add(resume);
    }

    @Override
    protected Resume performGet(String uuid, Object index) {
        return storage.get((Integer) index);
    }

    @Override
    protected void performUpdate(Resume resume, Object index) {
        storage.set((Integer) index, resume);
    }

    @Override
    protected void performDelete(String uuid, Object index) {
        storage.remove((int) index);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return storage.indexOf(resume);
            }
        }
        return -1;
    }
}