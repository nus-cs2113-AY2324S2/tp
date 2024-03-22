package log;

import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Logger;

public class FileLogger {
    private static final Path LOG_FILE_PATH = Path.of("./logs.log");

    public static void setupLogger() {
        try {
            Logger fileLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            FileHandler fileHandler = new FileHandler(LOG_FILE_PATH.toString(), true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileLogger.setUseParentHandlers(false);
            fileLogger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
