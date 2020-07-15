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
    protected boolean contains(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected void performSave(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage is full");
        }
        insertResume(resume, (Integer) index);
        size++;
    }

    @Override
    protected Resume performGet(String uuid, Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void performUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected void performDelete(String uuid, Object index) {
        int intIndex = (Integer) index;
        size--;
        if (intIndex != size) {
            fillGap(intIndex);
        }
        storage[size] = null;
    }

    // for array-based storage getSearchKey() returns index:
    // - positive, if storage has resume with the uuid
    // - negative otherwise (for SortedArrayStorage this negative index is also point of insertion)
    @Override
    protected abstract Integer getSearchKey(String uuid);

    protected abstract void fillGap(int deletedResumeIndex);

    protected abstract void insertResume(Resume resume, int index);
}