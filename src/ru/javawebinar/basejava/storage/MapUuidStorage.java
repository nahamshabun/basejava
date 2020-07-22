package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapUuidStorage extends AbstractMapStorage<String> {

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean contains(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected Resume performGet(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void performDelete(String uuid) {
        storage.remove(uuid);
    }
}
