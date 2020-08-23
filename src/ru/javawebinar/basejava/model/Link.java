package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {
    private static final long serialVersionUID = 1L;

    private String url;
    private String text;

    public Link() {
    }

    public Link(String url, String text) {
        this.url = url;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(url, link.url) &&
                Objects.equals(text, link.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, text);
    }

    @Override
    public String toString() {
        return "<a href=\"" + url + "\">" + text + "</a>";
    }
}
