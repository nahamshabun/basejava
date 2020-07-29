package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Arrays;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume testResume = new Resume("Тест Тестович");

        testResume.addContact(ContactType.PHONE, "89991234567");
        testResume.addContact(ContactType.SKYPE, "https://github.com/test");
        testResume.addContact(ContactType.EMAIL, "test@test.com");
        testResume.addContact(ContactType.LINKED_IN, "https://linkedin.com/test");
        testResume.addContact(ContactType.GITHUB, "https://github.com/test");
        testResume.addContact(ContactType.STACK_OVERFLOW, "https://stackoverflow.com/test");
        testResume.addContact(ContactType.WEBSITE, "https://mytest.com");

        testResume.addSection(SectionType.OBJECTIVE, new TextSection("Тестировщик"));
        testResume.addSection(SectionType.PERSONAL, new TextSection("Хорошо тестирую"));
        testResume.addSection(SectionType.ACHIEVEMENTS, new ListSection(Arrays.asList("Тестировал", "А также тестировал")));
        testResume.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("TestSE", "TestEE")));

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
        testResume.addSection(SectionType.EXPERIENCE, new OrganizationSection(Arrays.asList(org1, org2)));

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
        testResume.addSection(SectionType.EDUCATION, new OrganizationSection(Arrays.asList(ed1, ed2)));

        System.out.println(testResume);
    }
}
