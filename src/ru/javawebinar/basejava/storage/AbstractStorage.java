package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        final String uuid = resume.getUuid();
        if (exists(uuid)) {
            throw new ExistStorageException(uuid);
        }
        performSave(resume);
    }

    @Override
    public Resume get(String uuid) {
        if (!exists(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return performGet(uuid);
    }

    @Override
    public void update(Resume resume) {
        final String uuid = resume.getUuid();
        if (!exists(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        performUpdate(resume, uuid);
    }

    @Override
    public void delete(String uuid) {
        if (!exists(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        performDelete(uuid);
    }

    protected abstract boolean exists(String uuid);
    protected abstract void performSave(Resume resume);
    protected abstract Resume performGet(String uuid);
    protected abstract void performUpdate(Resume resume, String uuid);
    protected abstract void performDelete(String uuid);
}
