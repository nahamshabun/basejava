package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Arrays;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume testResume = new Resume("Тест Тестович");

        testResume.addContact(ContactType.EMAIL, "test@test.com");
        testResume.addContact(ContactType.PHONE, "89991234567");
        testResume.addContact(ContactType.GITHUB, "https://github.com/test");

        testResume.addSection(SectionType.OBJECTIVE, new TextSection("Тестировщик"));
        testResume.addSection(SectionType.PERSONAL, new TextSection("Хорошо тестирую"));
        testResume.addSection(SectionType.ACHIEVEMENTS, new ListSection(Arrays.asList("Тестировал", "А также тестировал")));
        testResume.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("TestSE", "TestEE")));

        Organization org1 = new Organization("TestPlace1",
                new Link("tp1.com", "Test Place 1"),
                YearMonth.of(2018, 1),
                YearMonth.of(2018, 10),
                "testPlace1 title",
                "testPlace1 description");
        Organization org2 = new Organization("TestPlace2",
                new Link("tp2.com", "Test Place 2"),
                YearMonth.of(2019, 1),
                YearMonth.of(2019, 10),
                "testPlace2 title",
                "testPlace2 description");
        testResume.addSection(SectionType.EXPERIENCE, new OrganizationSection(Arrays.asList(org1, org2)));

        Organization ed1 = new Organization("TestEducation1",
                new Link("ted1.com", "Test Education 1"),
                YearMonth.of(2016, 10),
                YearMonth.of(2017, 2),
                "testEducation1 title",
                "");
        Organization ed2 = new Organization("TestEducation2",
                new Link("ted2.com", "Test Education 2"),
                YearMonth.of(2017, 4),
                YearMonth.of(2017, 12),
                "testPlace2 title",
                "");
        testResume.addSection(SectionType.EDUCATION, new OrganizationSection(Arrays.asList(ed1, ed2)));

        System.out.println(testResume);
    }
}
