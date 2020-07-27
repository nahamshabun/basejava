package ru.javawebinar.basejava.model;

import java.util.List;

public class OrganizationSection extends AbstractSection {
    private final List<Organization> organizations;

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizations=" + organizations +
                '}';
    }
}
