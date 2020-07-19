package ru.javawebinar.basejava.model;

import java.util.Comparator;
import java.util.UUID;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;

    public static final Comparator<Resume> FULL_NAME_COMPARATOR =
            Comparator.comparing(Resume::getFullName);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Resume resume = (Resume) obj;
        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode() + fullName.hashCode();
    }

    @Override
    public String toString() {
        return uuid + ": " + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int result = fullName.compareTo(o.fullName);
        if (result == 0) {
            result = uuid.compareTo(o.uuid);
        }
        return result;
    }
}