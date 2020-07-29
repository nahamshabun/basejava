package ru.javawebinar.basejava.model;

import java.util.List;

public class Organization {
    private final List<Period> periods;
    private final Link homePage;

    public Organization(String name, String url, List<Period> periods) {
        this.homePage = new Link(url, name);
        this.periods = periods;
    }

    @Override
    public String toString() {
        return homePage + "\n" + periods;
    }
}
