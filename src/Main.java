import com.company.Menu;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new FileCleaner(), 0, 10, TimeUnit.MINUTES);
        Menu menu = new Menu();
        menu.runMenu();
    }
}