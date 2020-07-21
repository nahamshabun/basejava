package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> UUID_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "helper");
        return Arrays.binarySearch(storage, 0, size, searchKey, UUID_COMPARATOR);
    }

    @Override
    protected void insertResume(Resume resume, int index) {
        // method receives negative index, then it's transformed into a valid one
        int insertionIndex = Math.abs(index + 1);
        System.arraycopy(storage, insertionIndex, storage, insertionIndex + 1, size - insertionIndex);
        storage[insertionIndex] = resume;
    }

    @Override
    protected void fillGap(int deletedResumeIndex) {
        System.arraycopy(storage, deletedResumeIndex + 1, storage, deletedResumeIndex, size - deletedResumeIndex);
    }
}
