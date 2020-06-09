package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = size - 1; i >= 0; i--) {
            storage[i] = null;
        }
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
        for (int i = resumeIndex; i < size; i++) {
            storage[i] = storage[i + 1];
        }
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
        int resumeIndex = -1;

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                resumeIndex = i;
                break;
            }
        }

        return resumeIndex;
    }
}