package ru.javawebinar.basejava.model;

import java.time.YearMonth;

public class Period {
    private final YearMonth from;
    private final YearMonth to;
    private final String title;
    private final String description;

    public Period(YearMonth from, YearMonth to, String title, String description) {
        this.from = from;
        this.to = to;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\t" + from + " - " + to + "\t" + title + "\t" + description + "\n";
    }
}
