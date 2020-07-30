package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.ResumeTestData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    private static final Resume resume1 = ResumeTestData.getTestResume("uuid1", "fullName1");
    private static final Resume resume2 = ResumeTestData.getTestResume("uuid2", "fullName2");
    private static final Resume resume3 = ResumeTestData.getTestResume("uuid3", "fullName3");
    private static final int sizeBeforeTest = 3;
    Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    private void setUp() {
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Nested
    @DisplayName("save() method tests")
    class SaveTests {

        @Test
        @DisplayName("when resume is found")
        void testResumeExist() {
            assertThrows(ExistStorageException.class, () -> storage.save(resume1));
        }

        @Test
        @DisplayName("when resume not found")
        void testResumeNotExist() {
            assertDoesNotThrow(() -> storage.save(ResumeTestData.getTestResume("valid", "valid")));
            assertEquals(sizeBeforeTest + 1, storage.size());
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
            assertDoesNotThrow(() -> storage.delete("uuid1"));
            assertEquals(sizeBeforeTest - 1, storage.size());
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
            assertDoesNotThrow(() -> storage.get("uuid1"));
            assertEquals(resume1, storage.get("uuid1"));
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
            assertDoesNotThrow(() -> storage.update(ResumeTestData.getTestResume("uuid1", "updatedFullName")));
            assertEquals(sizeBeforeTest, storage.size());
            assertEquals("updatedFullName", storage.get("uuid1").getFullName());
        }
    }

    @Test
    void testClear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void testSize() {
        assertEquals(sizeBeforeTest, storage.size());
    }

    @Test
    void testGetAllSorted() {
        List<Resume> expected = new ArrayList<>();
        expected.add(resume1);
        expected.add(resume2);
        expected.add(resume3);
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