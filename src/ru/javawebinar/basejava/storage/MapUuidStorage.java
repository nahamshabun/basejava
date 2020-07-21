package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean contains(Object uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected Resume performGet(Object uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void performDelete(Object uuid) {
        storage.remove(uuid);
    }
}
