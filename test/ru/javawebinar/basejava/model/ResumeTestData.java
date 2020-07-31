package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.UUID;

public class ResumeTestData {
    public static Resume getInstance(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE, "89991234567");
        resume.addContact(ContactType.SKYPE, "https://github.com/test");
        resume.addContact(ContactType.EMAIL, "test@test.com");
        resume.addContact(ContactType.LINKED_IN, "https://linkedin.com/test");
        resume.addContact(ContactType.GITHUB, "https://github.com/test");
        resume.addContact(ContactType.STACK_OVERFLOW, "https://stackoverflow.com/test");
        resume.addContact(ContactType.WEBSITE, "https://mytest.com");

        resume.addSection(SectionType.OBJECTIVE, new TextSection("Тестировщик"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Хорошо тестирую"));
        resume.addSection(SectionType.ACHIEVEMENTS, new ListSection(Arrays.asList("Тестировал", "А также тестировал")));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("TestSE", "TestEE")));

        Organization org1 = new Organization("Org1", "org1.com", Arrays.asList(
                new Period(YearMonth.of(2018, 1), YearMonth.of(2018, 5),
                        "org1 period1 title", "org1 period1 " + "description"),
                new Period(YearMonth.of(2018, 6), YearMonth.of(2019, 1),
                        "org1 period2 title", "org1 " + "period2 description")));
        Organization org2 = new Organization("Org2", "org2.com", Arrays.asList(
                new Period(YearMonth.of(2019, 2), YearMonth.of(2019, 8),
                        "org2 period1 title", "org1 period1 description"),
                new Period(YearMonth.of(2018, 6), YearMonth.of(2019, 1),
                        "org2 period2 title", "org1 period2 description")));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(Arrays.asList(org1, org2)));

        Organization ed1 = new Organization("ed1", "ed1.com", Arrays.asList(
                new Period(YearMonth.of(2018, 1), YearMonth.of(2018, 5),
                        "ed1 period1 title", "ed1 period1 " + "description"),
                new Period(YearMonth.of(2018, 6), YearMonth.of(2019, 1),
                        "ed1 period2 title", "ed1 " + "period2 description")));
        Organization ed2 = new Organization("ed2", "ed2.com", Arrays.asList(
                new Period(YearMonth.of(2019, 2), YearMonth.of(2019, 8),
                        "ed2 period1 title", "ed1 period1 description"),
                new Period(YearMonth.of(2018, 6), YearMonth.of(2019, 1),
                        "ed2 period2 title", "ed1 period2 description")));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(Arrays.asList(ed1, ed2)));
        System.out.println("resume created and returned");
        return resume;
    }


    public static Resume getInstance(String fullName) {
        return getInstance(UUID.randomUUID().toString(), fullName);
    }
}
