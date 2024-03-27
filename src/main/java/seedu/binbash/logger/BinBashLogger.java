package seedu.binbash.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BinBashLogger {
    private static FileHandler fileHandler;
    private static final String logDirectoryPath = "./logs/";
    private static final String logFileName = "logs.txt";
    private static boolean isLogFileCreated = false;
    private final Logger fileLogger;
    private final Logger consoleLogger;

    public BinBashLogger(String loggerName) {
        fileLogger = Logger.getLogger(loggerName);
        consoleLogger = Logger.getLogger("consoleLogger");

        if(!isLogFileCreated) {
            createLogFile();
        }
    }

    private void createLogFile() {
        File logDirectory = new File(logDirectoryPath);
        File logFile = new File(logDirectory, logFileName);

        //Remove old log file to create a new fresh log file
        logFile.delete();

        if (!logDirectory.exists()) {
            boolean wasDirectoryMade = logDirectory.mkdirs();
            if (!wasDirectoryMade) {
                consoleLogger.log(Level.WARNING, "Could not create log directory.");
            }
        }

        if (!logFile.exists()) {
            boolean wasLogFileCreated = false;
            try {
                wasLogFileCreated = logFile.createNewFile();
            } catch (IOException e) {
                consoleLogger.log(Level.WARNING, "Could not create log file.");
            }

            if(!wasLogFileCreated) {
                consoleLogger.log(Level.WARNING, "Could not create log file.");
            }
        }

        assert logDirectory.exists() : "Log directory should already exist / have been created";
        assert logFile.exists() : "Log file (logs.txt) should already exists / have been created";

        isLogFileCreated = true;
    }

    public void info(String message) {
        if(isLogFileCreated) {
            setFileHandler();
            fileLogger.log(Level.INFO, message);
            closeFileHandler();
        }
    }

    public void warning(String message) {
        if(isLogFileCreated) {
            setFileHandler();
            fileLogger.log(Level.WARNING, message);
            closeFileHandler();
        }
    }

    public void severe(String message) {
        if(isLogFileCreated) {
            setFileHandler();
            fileLogger.log(Level.SEVERE, message);
            closeFileHandler();
        }
    }

    private void setFileHandler() {
        try {
            fileHandler = new FileHandler(logDirectoryPath + logFileName, true);
        } catch (IOException e) {
            consoleLogger.log(Level.WARNING, "Could not create file handler! Unable to generate logs!");
        }

        fileHandler.setFormatter(new SimpleFormatter());
        fileLogger.setUseParentHandlers(false);
        fileLogger.addHandler(fileHandler);
    }

    private void closeFileHandler() {
        fileHandler.close();
    }
}
