package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("Directory " + directory.getAbsolutePath() + " is empty");
            return;
        }
        for (File resume : files) {
            if (!resume.delete()) {
                System.out.println("File " + resume.getName() + " wasn't deleted");
            }
        }
    }

    @Override
    public int size() {
        String[] fileList = directory.list();
        return fileList != null ? fileList.length : 0;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void performUpdate(Resume resume, File file) {
        try {
            write(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected boolean contains(File file) {
        return file.exists();
    }

    @Override
    protected void performSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        performUpdate(resume, file);
    }

    @Override
    protected Resume performGet(File file) {
        try {
            return read(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Read error", file.getName(), e);
        }
    }


    @Override
    protected void performDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected List<Resume> performGetAll() {
        List<Resume> resumes = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                    resumes.add(performGet(file));
            }
        }
        return resumes;
    }

    protected abstract void write(Resume r, OutputStream outputStream) throws IOException;

    protected abstract Resume read(InputStream inputStream) throws IOException;
}
