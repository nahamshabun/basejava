package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Period> periods;
    private final Link homePage;

    public Organization(String name, String url, Period... periods) {
        this(new Link(url, name), Arrays.asList(periods));
    }

    public Organization(Link homePage, List<Period> periods) {
        this.homePage = homePage;
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(periods, that.periods) &&
                Objects.equals(homePage, that.homePage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periods, homePage);
    }

    @Override
    public String toString() {
        return homePage + "\n" + periods;
    }

    public static class Period implements Serializable {
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return Objects.equals(from, period.from) &&
                    Objects.equals(to, period.to) &&
                    Objects.equals(title, period.title) &&
                    Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, title, description);
        }

        @Override
        public String toString() {
            return "\t" + from + " - " + to + "\t" + title + "\t" + description + "\n";
        }
    }
}
