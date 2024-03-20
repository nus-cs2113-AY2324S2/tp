package grocery;

import java.io.File;
import java.io.IOException;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

/**
 * Logs metadata about the GroceryList class.
 */
public class LoggerGroceryList {
    /**
     * Configure logger for the GroceryList class.
     */
    public static void setupLogger() {
        Logger loggerGL = Logger.getLogger(GroceryList.class.getName());

        LogManager.getLogManager().reset();
        loggerGL.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        loggerGL.addHandler(ch);

        try {
            String filepath = new File("logs/GroceryList.log").getAbsolutePath();
            FileHandler fh = new FileHandler(filepath, true);
            fh.setLevel(Level.INFO);
            loggerGL.addHandler(fh);
        } catch (IOException e) {
            loggerGL.log(Level.SEVERE, "Logger for GroceryList class fails", e);
        }
    }

}
