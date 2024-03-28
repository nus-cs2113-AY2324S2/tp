package seedu.planus;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

/**
 * The main class that represents the PlaNus application.
 */
public class PlaNus {
    private static Logger logger = Logger.getLogger("myLogger");
    private static FileHandler fileTxt;
    private static SimpleFormatter formatterTxt;


    private Timetable timetable;
    private boolean isExit;

    /**
     * Constructs a new PlaNus object.
     */
    public PlaNus() {
        isExit = false;
    }

    /**
     * Sets up the logger
     */
    public void setUpLogger() {
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        logger.setLevel(Level.INFO);
        try {
            fileTxt = new FileHandler("Logging.txt", true);
        } catch (IOException e) {
            throw new RuntimeException("Problems with creating the log files");
        }
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }

    /**
     * Runs the PlaNus application.
     */
    public void run() {
        setUpLogger();
        Ui.printLogo();
        logger.log(Level.INFO, "Loading user timetable" + Storage.getUserTimetableFileName());
        timetable = Storage.loadTimetable(Storage.getUserTimetableFileName());

        while (!isExit) {
            String line = Ui.getUserCommand().trim();
            try {
                isExit = Parser.parseCommand(line, timetable);
            } catch (Exception e) {
                Ui.printErrorMessage(e.getMessage());
            }
            logger.log(Level.INFO, "Loading user timetable" + Storage.getUserTimetableFileName());
            timetable = Storage.loadTimetable(Storage.getUserTimetableFileName());
        }
        Ui.printExit();
    }
    /**
     * Main entry-point for the java.planus.PlaNus application.
     */
    public static void main(String[] args) {
        new PlaNus().run();
    }
}
