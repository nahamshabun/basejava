package ru.javawebinar.basejava.model;

import java.time.YearMonth;

public class Organization {
    private String name;
    private Link homePage;
    private YearMonth from;
    private YearMonth to;
    private String title;
    private String description;

    public Organization(String name, Link homePage, YearMonth from, YearMonth to, String title, String description) {
        this.name = name;
        this.homePage = homePage;
        this.from = from;
        this.to = to;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", homePage=" + homePage +
                ", from=" + from +
                ", to=" + to +
                ", descriptionTitle='" + title + '\'' +
                ", descriptionBody='" + description + '\'' +
                '}';
    }
}
