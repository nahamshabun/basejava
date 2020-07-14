package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected final ArrayList<Resume> storage = new ArrayList<>();

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
    protected boolean has(String uuid) {
            return getSearchKey(uuid) >= 0;
    }

    @Override
    protected void performSave(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume performGet(String uuid) {
        return storage.get(getSearchKey(uuid));
    }

    @Override
    protected void performUpdate(Resume resume, String uuid) {
        storage.set(getSearchKey(uuid), resume);
    }

    @Override
    protected void performDelete(String uuid) {
        storage.remove((int)getSearchKey(uuid));
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