package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.XMLParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XMLStreamSerializer implements StreamSerializer {
    private XMLParser xmlParser;

    public XMLStreamSerializer() {
        xmlParser = new XMLParser(Resume.class,
                                  Organization.class,
                                  Link.class,
                                  OrganizationSection.class,
                                  TextSection.class,
                                  ListSection.class,
                                  Organization.Period.class);
    }

    @Override
    public void write(Resume resume, OutputStream outputStream) throws IOException {
        try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            xmlParser.marshal(resume, writer);
        }
    }

    @Override
    public Resume read(InputStream inputStream) throws IOException {
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshal(reader);
        }
    }
}
