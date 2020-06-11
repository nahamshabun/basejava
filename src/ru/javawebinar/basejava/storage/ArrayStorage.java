package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String resumeUuid = resume.getUuid();
        int resumeIndex = getResumeIndex(resumeUuid);

        if (resumeIndex == -1) {
            System.out.println("There's no resume with id \"" + resumeUuid + "\" in storage");
            return;
        }

        storage[resumeIndex] = resume;
    }

    public void save(Resume resume) {
        if (size ==  storage.length) {
            System.out.println("Storage is full");
            return;
        }

        String resumeUuid = resume.getUuid();
        int resumeIndex = getResumeIndex(resumeUuid);

        if (resumeIndex != -1) {
            System.out.println("Resume with id \"" + resumeUuid + "\" already exists");
            return;
        }

        storage[size] = resume;
        size++;
    }

    public Resume get(String uuid) {
        int resumeIndex = getResumeIndex(uuid);

        if (resumeIndex == -1) {
            System.out.println("There's no resume with id \"" + uuid + "\" in storage");
            return null;
        }

        return storage[resumeIndex];
    }

    public void delete(String uuid) {
        int resumeIndex = getResumeIndex(uuid);

        if (resumeIndex == -1) {
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

    public int size() {
        return size;
    }

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }
}