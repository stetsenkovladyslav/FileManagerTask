import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Timer;
import java.util.TimerTask;

public class FileCleaner {
    private static FileCleaner fileCleaner;
    private final Timer timer;

    private FileCleaner() {
        this.timer = new Timer(true);
    }

    public static FileCleaner getInstance() {
        if (fileCleaner == null) {
            fileCleaner = new FileCleaner();
        }
        return fileCleaner;
    }

    public void start() {
        timer.schedule(new FileCleanerTask(), 0, 600000);
    }

    static class FileCleanerTask extends TimerTask {
        @Override
        public void run() {
            try {
                DirectoryStream<Path> paths = Files.newDirectoryStream(Path.of("files/"));
                var iterator = paths.iterator();
                while (iterator.hasNext()) {
                    Path filePath = iterator.next();
                    BasicFileAttributes fileAttributes = Files.readAttributes(filePath, BasicFileAttributes.class);
                    long lastAccessTimeInMillis = fileAttributes.lastAccessTime().toMillis();
                    if (System.currentTimeMillis() - lastAccessTimeInMillis >= 300000) {
                        Files.delete(filePath);
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}