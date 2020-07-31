package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.ResumeTestData;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        super(storage);
    }

    @Test
    // @DisplayName("test save() method when storage is full")
    void testFullStorage() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT - sizeBeforeTest; i++) {
                storage.save(ResumeTestData.getInstance("fullName" + i));
            }
        });
        assertThrows(StorageException.class, () -> storage.save(ResumeTestData.getInstance("fullName")));
    }
}
