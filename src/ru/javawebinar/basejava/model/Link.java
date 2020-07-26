package ru.javawebinar.basejava.model;

public class Link {
    private String url;
    private String text;

    public Link(String url, String text) {
        this.url = url;
        this.text = text;
    }

    @Override
    public String toString() {
        return "<a href=\"" + url + "\">" + text + "</a>";
    }
}
