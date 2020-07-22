package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
    protected boolean contains(Integer index) {
        return index >= 0;
    }

    @Override
    protected List<Resume> performGetAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected void performSave(Resume resume, Integer index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage is full");
        }
        insertResume(resume, index);
        size++;
    }

    @Override
    protected Resume performGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void performUpdate(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    protected void performDelete(Integer index) {
        size--;
        fillGap(index);
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