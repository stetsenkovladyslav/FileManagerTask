import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.TimerTask;

public class FileCleaner extends TimerTask {
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