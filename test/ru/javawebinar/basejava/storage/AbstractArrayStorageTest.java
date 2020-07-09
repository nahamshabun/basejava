package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {

    protected AbstractArrayStorage storage;

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        this.storage = storage;
    }

    @Test
    void testSize() {
        int expected = 10;
        fillStorage(expected);
        assertEquals(expected, storage.size());
    }

    @Test
    void testClear() {
        int num = 11;
        fillStorage(num);
        storage.clear();
        checkForAllNulls();
        assertEquals(0, storage.size());
    }

    @Nested
    class UpdateTests {

        @Test
        @DisplayName("when resume is not found")
        void testResumeNotFound() {
            assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
        }

        @Test
        @DisplayName("when resume is found")
        void testResumeFound() {
            storage.save(new Resume("valid"));
            assertDoesNotThrow(() -> storage.update(new Resume("valid")));
            assertEquals(1, storage.size());
        }
    }

    @Nested
    @DisplayName("save() method tests")
    class SaveTests {

        @Test
        @DisplayName("when storage already has this resume")
        void testResumeExists() {
            Resume resume = new Resume("test");
            storage.save(resume);
            int sizeBeforeSave = storage.size();
            assertThrows(ExistStorageException.class, () -> storage.save(resume));
            assertEquals(sizeBeforeSave, storage.size());
        }

        @Test
        @DisplayName("when storage is full")
        void testFullStorage() {
            assertDoesNotThrow(() -> fillStorage(AbstractArrayStorage.STORAGE_LIMIT));
            int sizeBeforeSave = storage.size();
            assertThrows(StorageException.class, () -> storage.save(new Resume()));
            assertEquals(sizeBeforeSave, storage.size());
        }

        @Test
        @DisplayName("when storage doesn't have this resume")
        void testResumeNotExist() {
            int sizeBeforeSave = storage.size();
            assertDoesNotThrow(() -> storage.save(new Resume()));
            assertEquals(sizeBeforeSave + 1, storage.size());
        }
    }

    @Nested
    @DisplayName("delete() method tests")
    class DeleteTests {

        @Test
        @DisplayName("when resume not found")
        void testResumeNotFound() {
            int sizeBeforeDelete = storage.size();
            assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
            assertEquals(sizeBeforeDelete, storage.size());
        }

        @Test
        @DisplayName("when resume is found")
        void testResumeFound() {
            storage.save(new Resume("valid"));
            int sizeBeforeDelete = storage.size();
            assertDoesNotThrow(() -> storage.delete("valid"));
            assertEquals(sizeBeforeDelete - 1, storage.size());
            assertNull(storage.storage[storage.size()]);
            checkForAllNonNulls();
        }
    }

    @Test
    void testGetAll() {
        int expectedSize = 13;
        Resume[] expected = new Resume[expectedSize];
        for (int i = 0; i < expectedSize; i++) {
            Resume resume = new Resume("uuid" + i);
            expected[i] = resume;
            storage.save(resume);
        }
        if (storage instanceof SortedArrayStorage) {
            Arrays.sort(expected);
        }
        assertArrayEquals(expected, storage.getAll());
    }

    @Nested
    @DisplayName("get() method tests")
    class GetTests {

        @Test
        @DisplayName("when there is no resume with this uuid in storage")
        void testUuidNotFound() {
            assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
        }

        @Test
        @DisplayName("when storage has resume with this uuid")
        void testUuidFound() {
            storage.save(new Resume("valid"));
            assertDoesNotThrow(() -> storage.get("valid"));
            assertEquals("valid", storage.get("valid").getUuid());
        }
    }

    // helper methods for tests

    void fillStorage(int numOfResumes) {
        for (int i = 0; i < numOfResumes; i++) {
            storage.save(new Resume());
        }
    }

    void checkForAllNulls() {
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            if (storage.storage[i] != null) {
                fail("null expected at index of " + i);
            }
        }
    }

    void checkForAllNonNulls() {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.storage[i] == null) {
                fail("element with index of " + i + " should not be null");
            }
        }
    }
}