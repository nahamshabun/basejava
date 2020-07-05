package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        storage[index] = resume;
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage is full");
        }

        String uuid = resume.getUuid();
        int index = getResumeIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }

        insertResume(resume);
        size++;
    }

    public void delete(String uuid) {
        int index = getResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        size--;
        if (index != size) {
            fillGap(index);
        }
        storage[size] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        return storage[index];
    }

    protected abstract void fillGap(int deletedResumeIndex);
    protected abstract void insertResume(Resume resume);
    protected abstract int getResumeIndex(String uuid);
}