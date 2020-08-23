package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.XMLStreamSerializer;

class XMLPathStorageTest extends AbstractStorageTest {
    public XMLPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XMLStreamSerializer()));
    }
}