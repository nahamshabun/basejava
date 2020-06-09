package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int storageSize = 0;

    public void clear() {
        for (int i = storageSize - 1; i >= 0; i--) {
            storage[i] = null;
        }
        storageSize = 0;
    }

    public void save(Resume r) {
        storage[storageSize] = r;
        storageSize++;
    }

    public Resume get(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        return resumeIndex != -1 ? storage[resumeIndex] : null;
    }

    public void delete(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex != -1) {
            storageSize--;
            for (int i = resumeIndex; i < storageSize; i++) {
                storage[i] = storage[i + 1];
            }
            storage[storageSize] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, storageSize);
    }

    public int size() {
        return storageSize;
    }

    private int getResumeIndex(String uuid) {
        int resumeIndex = -1;
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                resumeIndex = i;
                break;
            }
        }
        if (resumeIndex == -1) {
            System.out.println("There's no resume with id \"" + uuid + "\" in storage");
        }
        return resumeIndex;
    }
}