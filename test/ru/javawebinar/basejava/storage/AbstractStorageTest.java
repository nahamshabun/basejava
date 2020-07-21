package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Nested
    @DisplayName("save() method tests")
    class SaveTests {

        @Test
        @DisplayName("when resume is found")
        void testResumeExist() {
            Resume resume = new Resume("test");
            storage.save(resume);
            assertThrows(ExistStorageException.class, () -> storage.save(resume));
        }

        @Test
        @DisplayName("when resume not found")
        void testResumeNotExist() {
            int sizeBeforeSave = storage.size();
            assertDoesNotThrow(() -> storage.save(new Resume("testFullName")));
            assertEquals(sizeBeforeSave + 1, storage.size());
        }
    }

    @Nested
    @DisplayName("delete() method tests")
    class DeleteTests {

        @Test
        @DisplayName("when resume not found")
        void testResumeNotFound() {
            assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
        }

        @Test
        @DisplayName("when resume is found")
        void testResumeFound() {
            storage.save(new Resume("valid", "valid"));
            int sizeBeforeDelete = storage.size();
            assertDoesNotThrow(() -> storage.delete("valid"));
            assertEquals(sizeBeforeDelete - 1, storage.size());
        }
    }

    @Nested
    @DisplayName("get() method tests")
    class GetTests {

        @Test
        @DisplayName("when there not found")
        void testResumeNotFound() {
            assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
        }

        @Test
        @DisplayName("when resume is found")
        void testResumeFound() {
            Resume resume = new Resume("valid", "valid");
            storage.save(resume);
            assertDoesNotThrow(() -> storage.get("valid"));
            assertEquals(resume, storage.get("valid"));
        }
    }

    @Nested
    class UpdateTests {

        @Test
        @DisplayName("when resume not found")
        void testResumeNotFound() {
            assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
        }

        @Test
        @DisplayName("when resume is found")
        void testResumeFound() {
            storage.save(new Resume("valid", "valid"));
            assertDoesNotThrow(() -> storage.update(new Resume("valid", "updated")));
            assertEquals(1, storage.size());
            assertEquals("updated", storage.get("valid").getFullName());
        }
    }

    @Test
    void testClear() {
        int num = 11;
        fillStorage(num);
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void testSize() {
        int expected = 10;
        fillStorage(expected);
        assertEquals(expected, storage.size());
    }

    @Test
    void testGetAllSorted() {
        int expectedSize = 13;
        List<Resume> expected = new ArrayList<>();
        for (int i = 0; i < expectedSize; i++) {
            Resume resume = new Resume("uuid" + i, "fullName" + i);
            expected.add(resume);
            storage.save(resume);
        }
        List<Resume> actual = storage.getAllSorted();
        Collections.sort(expected);
        assertIterableEquals(expected, actual);
    }

    // helper methods
    void fillStorage(int numOfResumes) {
        for (int i = 0; i < numOfResumes; i++) {
            storage.save(new Resume("fullName" + i));
        }
    }
}