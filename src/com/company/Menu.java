package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final com.company.FileOperation fileOperation;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.fileOperation = new com.company.FileOperationImpl();
    }

    public int showMenu() {
        int select;

        System.out.print("""
                 |  Welcome to the File Manager |
                 Select operation:
                 1) create file
                 2) update file
                 3) drop file
                 4) delete text in file
                 5) exit on window
                """.indent(1));

        select = scanner.nextInt();
        return select;
    }

    public void runMenu() throws IOException {

        int select = showMenu();

        switch (select) {
            case 1 -> {
                fileOperation.createFile();
                runMenu();
            }
            case 2 -> {
                fileOperation.updateFile();
                runMenu();
            }
            case 3 -> {
                fileOperation.dropFile();
                runMenu();
            }
            case 4 -> {
                fileOperation.removeTextFromFile();
                runMenu();
            }
            case 5 -> {
                System.exit(0);
            }

        }

    }
}
