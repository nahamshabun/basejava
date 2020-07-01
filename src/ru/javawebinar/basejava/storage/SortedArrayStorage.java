package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getResumeIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume resume) {
        int insertionIndex = getResumeIndex(resume.getUuid());
        insertionIndex = Math.abs(insertionIndex + 1);
        System.arraycopy(storage, insertionIndex, storage, insertionIndex + 1, size - insertionIndex);
        storage[insertionIndex] = resume;
    }

    @Override
    protected void fillGap(int deletedResumeIndex) {
        System.arraycopy(storage, deletedResumeIndex + 1, storage, deletedResumeIndex, size - deletedResumeIndex);
    }
}
