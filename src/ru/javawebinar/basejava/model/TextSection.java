package ru.javawebinar.basejava.model;

public class TextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final String content;

    public TextSection(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content + "\n";
    }
}
