package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractMapStorage {

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean contains(Object resume) {
        return resume != null;
    }

    @Override
    protected Resume performGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void performDelete(Object resume) {
        storage.remove(((Resume) resume).getUuid());
    }
}
