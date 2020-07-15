package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object searchKey = getSearchKeyIfNotExist(resume.getUuid());
        performSave(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        return performGet(uuid, searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getSearchKeyIfExist(resume.getUuid());
        performUpdate(resume, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        performDelete(uuid, searchKey);
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

    protected abstract void performSave(Resume resume, Object searchKey);

    protected abstract Resume performGet(String uuid, Object searchKey);

    protected abstract void performUpdate(Resume resume, Object searchKey);

    protected abstract void performDelete(String uuid, Object searchKey);
}
