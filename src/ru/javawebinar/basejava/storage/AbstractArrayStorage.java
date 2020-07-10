package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected boolean exists(String uuid) {
        return getResumeIndex(uuid) >= 0;
    }

    @Override
    protected void performSave(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage is full");
        }
        insertResume(resume);
        size++;
    }

    @Override
    protected Resume performGet(String uuid) {
        return storage[getResumeIndex(uuid)];
    }

    @Override
    protected void performUpdate(Resume resume, String uuid) {
        storage[getResumeIndex(uuid)] = resume;
    }

    @Override
    protected void performDelete(String uuid) {
        int index = getResumeIndex(uuid);
        size--;
        if (index != size) {
            fillGap(index);
        }
        storage[size] = null;
    }

    protected abstract void fillGap(int deletedResumeIndex);
    protected abstract void insertResume(Resume resume);
    protected abstract int getResumeIndex(String uuid);
}