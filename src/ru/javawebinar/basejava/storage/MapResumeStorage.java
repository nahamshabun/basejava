package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractMapStorage<Resume> {

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean contains(Resume resume) {
        return resume != null;
    }

    @Override
    protected Resume performGet(Resume resume) {
        return resume;
    }

    @Override
    protected void performDelete(Resume resume) {
        storage.remove(resume.getUuid());
    }
}
