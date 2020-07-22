package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected Integer getSearchKey(String uuid) {
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return storage.indexOf(resume);
            }
        }
        return -1;
    }

    @Override
    protected boolean contains(Integer index) {
        return index >= 0;
    }

    @Override
    protected List<Resume> performGetAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected void performSave(Resume resume, Integer index) {
        storage.add(resume);
    }

    @Override
    protected Resume performGet(Integer index) {
        return storage.get(index);
    }

    @Override
    protected void performUpdate(Resume resume, Integer index) {
        storage.set(index, resume);
    }

    @Override
    protected void performDelete(Integer index) {
        storage.remove((int) index);
    }
}