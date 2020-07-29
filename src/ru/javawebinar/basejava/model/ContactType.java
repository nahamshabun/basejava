package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Тел."),
    SKYPE("Skype"),
    EMAIL("Почта"),
    LINKED_IN("Профиль LinkedIn"),
    GITHUB("Профиль Github"),
    STACK_OVERFLOW("Профиль StackOverflow"),
    WEBSITE("Домашняя страница");

    private final String title;

    public String getTitle() {
        return title;
    }

    ContactType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "\n" + title;
    }
}
