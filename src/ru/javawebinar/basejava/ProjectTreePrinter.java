package ru.javawebinar.basejava;

import java.io.File;

public class ProjectTreePrinter {

    public static void main(String[] args) {
        File projectRoot = new File(System.getProperty("user.dir"));
        printDirectoryTree(projectRoot, 0);
    }

    public static void printDirectoryTree(File directory, int offset) {
        String offsetString = getOffsetString(offset);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(offsetString + file.getName());
                if (file.isDirectory()) {
                    printDirectoryTree(file, offset + 1);
                }
            }
        }
    }

    public static String getOffsetString(int offset) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < offset; i++) {
            stringBuilder.append("\t");
        }
        return stringBuilder.toString();
    }
}
