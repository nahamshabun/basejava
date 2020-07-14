package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        super(storage);
    }

    @Test
    @DisplayName("test save() method when storage is full")
    void testFullStorage() {
        assertDoesNotThrow(() -> fillStorage(AbstractArrayStorage.STORAGE_LIMIT));
        assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }
}
