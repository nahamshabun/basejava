package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume resume) {
         storage[size] = resume;
    }

    // size was decremented (as the resume was deleted) - so it's the index of last element at this point
    @Override
    protected void fillGap(int deletedResumeIndex) {
        storage[deletedResumeIndex] = storage[size];
    }
}
