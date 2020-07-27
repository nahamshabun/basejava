package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> content;

    public ListSection(List<String> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "content=" + content +
                '}';
    }
}
