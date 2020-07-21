package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object searchKey = getSearchKeyIfNotExist(resume.getUuid());
        performSave(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        return performGet(searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getSearchKeyIfExist(resume.getUuid());
        performUpdate(resume, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        performDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = performGetAll();
        Collections.sort(result);
        return result;
    }


    private Object getSearchKeyIfNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (contains(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyIfExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!contains(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    // for array-based types of storage returns Integer index, for MapStorage - String uuid
    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean contains(Object searchKey);

    protected abstract List<Resume> performGetAll();

    protected abstract void performSave(Resume resume, Object searchKey);

    protected abstract Resume performGet(Object searchKey);

    protected abstract void performUpdate(Resume resume, Object searchKey);

    protected abstract void performDelete(Object searchKey);
}
