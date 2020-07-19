package ru.javawebinar.basejava.storage;

public class MapUuidStorage extends AbstractMapStorage{

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean contains(Object uuid) {
        return storage.get(uuid) != null;
    }
}
