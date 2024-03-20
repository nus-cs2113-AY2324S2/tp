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
            // TODO: /home/runner/work/tp/tp/text-ui-test///////tp/logs/GroceryList.log.clk
            // initialises it in text-ui-test ???
            // do not put parent dir bruh
            FileHandler fh = new FileHandler("GroceryList.log", true);
            fh.setLevel(Level.INFO);
            loggerGL.addHandler(fh);
        } catch (IOException e) {
            loggerGL.log(Level.SEVERE, "Logger for GroceryList class fails", e);
        }
    }

}
