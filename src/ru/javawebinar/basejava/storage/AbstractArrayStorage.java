package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

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
        String resumeUuid = resume.getUuid();
        int resumeIndex = getResumeIndex(resumeUuid);

        if (resumeIndex < 0) {
            System.out.println("There's no resume with id \"" + resumeUuid + "\" in storage");
            return;
        }

        storage[resumeIndex] = resume;
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is full");
            return;
        }

        String resumeUuid = resume.getUuid();
        int resumeIndex = getResumeIndex(resumeUuid);

        if (resumeIndex >= 0) {
            System.out.println("Resume with id \"" + resumeUuid + "\" already exists");
            return;
        }

        // if we get here, resumeIndex is negative
        // so actual insertion index is calculated on the go and then passed to the method
        insertResume(resume, Math.abs(resumeIndex + 1));
        size++;
    }

    public void delete(String uuid) {
        int resumeIndex = getResumeIndex(uuid);

        if (resumeIndex < 0) {
            System.out.println("There's no resume with id \"" + uuid + "\" in storage");
            return;
        }

        size--;
        if (size - resumeIndex >= 0)
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex);
        storage[size] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public Resume get(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex < 0) {
            System.out.println("There's no resume with id \"" + uuid + "\" in storage");
            return null;
        }
        return storage[resumeIndex];
    }

    protected abstract void insertResume(Resume resume, int insertionPoint);
    protected abstract int getResumeIndex(String uuid);
}