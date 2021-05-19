import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

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

    public void runMenu() throws UnsupportedAudioFileException, IOException {

        int select = showMenu();

        switch (select) {
            case 1 -> {
                FileOperation.createFile();
                runMenu();
            }
            case 2 -> {
                FileOperation.updateFile();
                runMenu();
            }
            case 3 -> {
                FileOperation.dropFile();
                runMenu();
            }
            case 4 -> {
                FileOperation.removeTextFromFile();
                runMenu();
            }
            case 5 -> System.exit(0);

        }

    }
}