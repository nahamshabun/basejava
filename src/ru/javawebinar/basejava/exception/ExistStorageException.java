package ru.javawebinar.basejava.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume with id '" + uuid + "' already exists in storage", uuid);
    }
}
