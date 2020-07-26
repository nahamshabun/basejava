package ru.javawebinar.basejava.model;

import java.time.YearMonth;

public class Organization {
    private String name;
    private Link homePage;
    private YearMonth from;
    private YearMonth to;
    private String descriptionTitle;
    private String descriptionBody;

    public Organization(String name, Link homePage, YearMonth from, YearMonth to, String descriptionTitle, String descriptionBody) {
        this.name = name;
        this.homePage = homePage;
        this.from = from;
        this.to = to;
        this.descriptionTitle = descriptionTitle;
        this.descriptionBody = descriptionBody;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", homePage=" + homePage +
                ", from=" + from +
                ", to=" + to +
                ", descriptionTitle='" + descriptionTitle + '\'' +
                ", descriptionBody='" + descriptionBody + '\'' +
                '}';
    }
}
