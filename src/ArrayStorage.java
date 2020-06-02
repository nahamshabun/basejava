import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int storageSize = 0;

    void clear() {
        storage = null;
        storageSize = 0;
    }

    void save(Resume r) {
        storage[storageSize] = r;
        storageSize++;
    }

    Resume get(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        return resumeIndex != -1 ? storage[resumeIndex] : null;
    }

    void delete(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex != -1) {
            storageSize--;
            for (int i = resumeIndex; i < storageSize; i++) {
                storage[i] = storage[i + 1];
            }
            storage[storageSize] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (storageSize == 0) {
            return new Resume[0];
        }
        return Arrays.copyOfRange(storage, 0, storageSize);
    }

    int size() {
        return storageSize;
    }

    private int getResumeIndex(String uuid) {
        int resumeIndex = -1;
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resumeIndex = i;
                break;
            }
        }
        if (resumeIndex == -1) {
            System.out.println("There's no resume with id \"" + uuid + "\" in storage");
        }
        return resumeIndex;
    }
}
