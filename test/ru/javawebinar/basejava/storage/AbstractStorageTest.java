package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.ResumeTestData;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("D:\\dev\\basejava\\storage");
    private static final Resume resume1 = ResumeTestData.getInstance("uuid1", "fullName1");
    private static final Resume resume2 = ResumeTestData.getInstance("uuid2", "fullName2");
    private static final Resume resume3 = ResumeTestData.getInstance("uuid3", "fullName3");
    protected static final int sizeBeforeTest = 3;
    Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    private void setUp() {
        storage.clear();
        storage.save(resume3);
        storage.save(resume1);
        storage.save(resume2);
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
        @DisplayName("when resume is not found")
        void testResumeNotExist() {
            Resume resume = ResumeTestData.getInstance("valid", "valid");
            assertDoesNotThrow(() -> storage.save(resume));
            assertEquals(resume, storage.get("valid"));
            assertEquals(sizeBeforeTest + 1, storage.size());
        }
    }

    @Nested
    @DisplayName("delete() method tests")
    class DeleteTests {

        @Test
        @DisplayName("when resume is not found")
        void testResumeNotExist() {
            assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
        }

        @Test
        @DisplayName("when resume is found")
        void testResumeExist() {
            assertDoesNotThrow(() -> storage.delete("uuid1"));
            assertEquals(sizeBeforeTest - 1, storage.size());
            assertThrows(NotExistStorageException.class, () -> storage.delete("uuid1"));
        }
    }

    @Nested
    @DisplayName("get() method tests")
    class GetTests {

        @Test
        @DisplayName("when resume is not found")
        void testResumeNotExist() {
            assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
        }

        @Test
        @DisplayName("when resume is found")
        void testResumeExist() {
            assertDoesNotThrow(() -> storage.get("uuid1"));
            assertEquals(resume1, storage.get("uuid1"));
        }
    }

    @Nested
    class UpdateTests {

        @Test
        @DisplayName("when resume is not found")
        void testResumeNotExist() {
            assertThrows(NotExistStorageException.class, () -> storage.update(ResumeTestData.getInstance("dummy")));
        }

        @Test
        @DisplayName("when resume is found")
        void testResumeExist() {
            Resume resume = ResumeTestData.getInstance("uuid1", "updatedFullName");
            assertDoesNotThrow(() -> storage.update(resume));
            assertEquals(resume, storage.get("uuid1"));
            assertEquals(sizeBeforeTest, storage.size());
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
        List<Resume> actual = storage.getAllSorted();
        List<Resume> expected = Arrays.asList(resume1, resume2, resume3);
        assertIterableEquals(expected, actual);
    }
}