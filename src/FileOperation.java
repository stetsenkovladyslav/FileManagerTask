import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FileOperation {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String filesPath = "files/";

    private static String currentTimeString() {
        return LocalTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public static void createFile() throws IOException {
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

    public static void updateFile() throws IOException {
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

    public static void dropFile() throws FileNotFoundException {
        System.out.print("Enter file name (HH:mm:ss): ");
        File newFile = new File(filesPath.concat(scanner.nextLine().concat(".txt")));
        if (newFile.delete()) {
            System.out.printf("file '%s' was deleted successfully\n", newFile.getName());
        } else {
            System.out.printf("file '%s' does not exist\n", newFile.getName());
        }
    }

    public static void removeTextFromFile() throws IOException {
        System.out.print("Enter file name (HH:mm:ss): ");
        String fileName = scanner.nextLine().concat(".txt");
        File newFile = new File(filesPath.concat(fileName));
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

    public static void exists(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }
}