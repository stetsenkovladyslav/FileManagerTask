package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class FileOperation {

    protected Scanner scanner;
    protected String filesPath;

    abstract void createFile() throws IOException;

    abstract void updateFile() throws IOException;

    abstract void dropFile() throws IOException;

    abstract void removeTextFromFile() throws IOException;

    protected void exists(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    protected String currentTimeString() {
        return LocalTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
