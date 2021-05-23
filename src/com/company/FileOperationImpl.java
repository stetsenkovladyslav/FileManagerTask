package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileOperationImpl extends com.company.FileOperation {

    public FileOperationImpl() {
        scanner = new Scanner(System.in);
        filesPath = "/Users/stetsenko.v.n/FileManagerTask/files/";
    }

    @Override
    public void createFile() throws IOException {
        File newFile = new File(filesPath.concat(currentTimeString().concat(".txt")));
        System.out.print("Write your text: \n");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(newFile.toPath())) {
            while (true) {
                String nextLine = scanner.nextLine();
                if (nextLine.equals("exit")) break;
                bufferedWriter.write(nextLine);
                bufferedWriter.newLine();
            }
        }
    }

    @Override
    public void updateFile() throws IOException {
        System.out.print("Enter file name (HH:mm:ss): ");
        File newFile = new File(filesPath.concat(scanner.nextLine().concat(".txt")));
        exists(newFile);
        System.out.println("Write new text to append: ");
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(newFile, true))) {
            while (true) {
                String nextLine = scanner.nextLine();
                if (nextLine.equals("exit")) break;
                bufferedWriter.write(nextLine);
                bufferedWriter.newLine();
            }
        }
    }

    @Override
    public void dropFile() throws IOException {
        System.out.print("Enter file name (HH:mm:ss): ");
        File newFile = new File(filesPath.concat(scanner.nextLine().concat(".txt")));
        if (newFile.delete()) {
            System.out.printf("file '%s' was deleted successfully\n", newFile.getName());
        } else {
            System.out.printf("file '%s' does not exist\n", newFile.getName());
        }
    }

    @Override
    public void removeTextFromFile() throws IOException {
        System.out.print("Enter file name (HH:mm:ss): ");
        String fileName = filesPath.concat(scanner.nextLine().concat(".txt"));
        File newFile = new File(fileName);
        exists(newFile);
        String text = Files.readString(Path.of(fileName));
        System.out.println("Write text to delete: ");
        while (true) {
            String inputLine = scanner.nextLine();
            if (inputLine.equals("exit")) break;
            text = text.replace(inputLine, "");
        }
        Files.writeString(Path.of(fileName), text);
    }
}