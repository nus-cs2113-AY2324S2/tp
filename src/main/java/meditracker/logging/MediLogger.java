package meditracker.logging;

import java.util.logging.Logger;

public class MediLogger {
    private static final String MEDILOGGER_NAME = "MediLogger";
    private static Logger mediLogger  = null;

    /**
     * Gets the default logger for the MediTracker project.
     * If for some reason the default logger is not initialised when this function is called,
     * initialise the default logger and logs a warning.
     *
     * @return The default logger for MediTracker project.
     */
    public static Logger getMediLogger(){
        if (mediLogger == null) {
            initialiseLogger();
            mediLogger.warning("Logger initialised from unintended function");
        }
        assert mediLogger != null;
        return mediLogger;
    }

    /**
     * Initialises the default logger to be used for the MediTracker project.
     * The logger will send all the logging messages to the console.
     * Currently, no support to write to a dedicated log file.
     */
    public static void initialiseLogger() {
        if (mediLogger != null) {
            mediLogger.warning("Logger " + MEDILOGGER_NAME + " has been initialised already");
            return;
        }

        mediLogger = Logger.getLogger(MEDILOGGER_NAME);
        mediLogger.info("Logger initialised");
    }
}
