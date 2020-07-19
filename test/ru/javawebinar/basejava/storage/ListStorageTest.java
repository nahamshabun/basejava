package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.extension.Extension;

public class ListStorageTest extends AbstractStorageTest implements Extension {
    public ListStorageTest() {
        super(new ListStorage());
    }
}