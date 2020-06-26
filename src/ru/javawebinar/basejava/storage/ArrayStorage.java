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

    // insertionPoint is only needed for SortedArrayStorage, so it's not used here
    @Override
    protected void insertResume(Resume resume, int insertionPoint) {
         storage[size] = resume;
    }

    // size was decremented (as the resume was deleted) - so it's the index of last element at this point
    @Override
    protected void fillTheGap(int deletedResumeIndex) {
        storage[deletedResumeIndex] = storage[size];
    }
}
