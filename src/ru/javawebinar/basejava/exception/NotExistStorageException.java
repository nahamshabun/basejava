package ru.javawebinar.basejava.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("No resume with id '" + uuid + "' in storage", uuid);
    }
}
