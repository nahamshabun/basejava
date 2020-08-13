package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<String> content;

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content + "\n";
    }
}
