package ru.javawebinar.basejava.model;

import java.io.Serializable;

public class Link implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String url;
    private final String text;

    public Link(String url, String text) {
        this.url = url;
        this.text = text;
    }

    @Override
    public String toString() {
        return "<a href=\"" + url + "\">" + text + "</a>";
    }
}
