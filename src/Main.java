import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
        Menu menu = new Menu();
        FileCleaner.getInstance().start();
        menu.runMenu();
    }
}