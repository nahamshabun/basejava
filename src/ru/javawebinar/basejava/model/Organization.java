package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.YearMonthAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Period> periods;
    private Link homePage;

    public Organization() {
    }

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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        @XmlJavaTypeAdapter(YearMonthAdapter.class)
        private YearMonth from;
        @XmlJavaTypeAdapter(YearMonthAdapter.class)
        private YearMonth to;
        private String title;
        private String description;

        public Period() {
        }

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
