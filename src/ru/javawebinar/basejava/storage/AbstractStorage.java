package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    @Override
    public void save(Resume resume) {
        SK searchKey = getSearchKeyIfNotExist(resume.getUuid());
        performSave(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getSearchKeyIfExist(uuid);
        return performGet(searchKey);
    }

    @Override
    public void update(Resume resume) {
        SK searchKey = getSearchKeyIfExist(resume.getUuid());
        performUpdate(resume, searchKey);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getSearchKeyIfExist(uuid);
        performDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = performGetAll();
        Collections.sort(result);
        return result;
    }


    private SK getSearchKeyIfNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (contains(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getSearchKeyIfExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!contains(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    // for array-based types of storage returns Integer index, for MapStorage - String uuid
    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean contains(SK searchKey);

    protected abstract List<Resume> performGetAll();

    protected abstract void performSave(Resume resume, SK searchKey);

    protected abstract Resume performGet(SK searchKey);

    protected abstract void performUpdate(Resume resume, SK searchKey);

    protected abstract void performDelete(SK searchKey);
}
