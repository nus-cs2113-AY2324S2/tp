package seedu.budgetbuddy;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingConfig {
    public static void configure() {
        Logger logger = Logger.getLogger("");
        try {
            FileHandler fileHandler = new FileHandler("budget_buddy.log");
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.warning("Failed to configure logging: " + e.getMessage());
        }
    }
}
