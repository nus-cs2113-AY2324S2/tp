package supertracker;

import supertracker.command.Command;
import supertracker.command.InvalidCommand;
import supertracker.command.QuitCommand;
import supertracker.parser.Parser;
import supertracker.storage.FileManager;
import supertracker.ui.ErrorMessage;
import supertracker.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuperTracker {
    private static final Logger logger = Logger.getLogger(SuperTracker.class.getName());
    private static final String START_MESSAGE = "Starting SuperTracker application";
    private static final String EXIT_MESSAGE = "Exiting SuperTracker application";
    private static final String LOG_FILE_LOCATION = "supertracker.log";
    private static final String COMMAND_LOG = "Command passed successfully:";
    private static final String UNSUCCESSFUL_COMMAND_LOG = "Error while passing input: ";

    /**
     * Main entry-point for the java.supertracker.SuperTracker application.
     */
    public static void main(String[] args) {
        run();
    }

    /**
     * Runs the java.supertracker.SuperTracker application.
     */
    private static void run() {
        setupLogger();
        logger.info(START_MESSAGE);

        try {
            FileManager.loadData();
        } catch (IOException e) {
            Ui.printError(ErrorMessage.FILE_CORRUPTED_ERROR);
        }

        Ui.greetUser();
        handleCommands();

        logger.info(EXIT_MESSAGE);
    }

    private static void handleCommands() {
        Scanner in = new Scanner(System.in);
        Command command;
        do {
            String input = in.nextLine();
            Ui.printLine();
            try {
                command = Parser.parseCommand(input.trim());
                command.execute();
                logger.log(Level.INFO, COMMAND_LOG,command);
            } catch (TrackerException e) {
                Ui.printError(e.getErrorMessage());
                logger.log(Level.INFO, UNSUCCESSFUL_COMMAND_LOG + e.getErrorMessage(), e);
                command = new InvalidCommand();
            }
            Ui.printLine();
        } while (!command.isQuit());
        
        assert command instanceof QuitCommand;
        in.close();
    }

    private static void setupLogger() {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_LOCATION);
            fileHandler.setLevel(Level.INFO); // Set desired log level
            logger.setUseParentHandlers(false); // Disable console output for simplicity
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, ErrorMessage.FILE_HANDLER_ERROR, e);
        }
    }
}
