package seedu.duke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents user input parsing and handling
 * before providing feedback to the user.
 */
public class Handler {

    /**
     * Processes user input and filters for valid command words from enum {@code Command},
     * then creates the relevant {@code Task} object based on details entered.
     *
     * @throws CustomException If an error occurs during command processing.
     */
    public static void processInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (true) {
            // Convert command to uppercase before processing
            String instruction = userInput.toUpperCase().split(" ")[0];

            try {
                Command command = Command.valueOf(instruction);

                switch (command) {
                case EXIT:
                    return;

                case LOAD:

                    handleLoad(userInput);

                    break;
                case NEW:

                    handleNew(userInput);

                    break;
                case HISTORY:

                    handleHistory(userInput);

                    break;
                case LATEST:

                    handleLatest(userInput);

                    break;
                case EXERCISE:

                    handleExercise(userInput);

                    break;
                case HEALTH:

                    handleHealth(userInput);

                    break;
                case HEIGHT:

                    handleHeight(userInput);

                    break;
                case WEIGHT:

                    handleWeight(userInput);

                    break;

                case BMI:

                    handleBmi(userInput);

                    break;

                case START:

                    handleStart(userInput);

                    break;

                case END:

                    handleEnd(userInput);

                    break;

                case TODAY:

                    handleToday(userInput);

                    break;

                case LENGTH:

                    handleLength(userInput);

                    break;

                case HELP:

                    Reply.printHelp();

                    break;

                default:
                    throw new CustomException();
                }
            } catch (IllegalArgumentException e) {
                Reply.printException(e, Constant.INVALID_COMMAND);
            } catch (CustomException e) {
                Reply.printException(e);
            }

            userInput = in.nextLine();
        }
    }


    /**
     * Constructs a new {@code }  object based on the user input.
     *
     * @param tasks The tasks list to be managed, adding the relevant {@code Event} object into the list.
     * @param userInput The user input string.
     * @throws CustomException If the {@code Event} details are unspecified or invalid.
     */
    public static void handleExercise(String userInput){};
    public static void handleLoad(String userInput){};
    public static void handleNew(String userInput){};
    public static void handleHistory(String userInput){};
    public static void handleLatest(String userInput){};
    public static void handleHealth(String userInput){};
    public static void handleHeight(String userInput){};
    public static void handleWeight(String userInput){};
    public static void handleBmi(String userInput){};
    public static void handleStart(String userInput){};
    public static void handleEnd(String userInput){};
    public static void handleToday(String userInput){};
    public static void handleLength(String userInput){};



    /**
     * Initializes the Jarvas bot by printing a welcome message, loading tasks from storage,
     * and returning the tasks list.
     */
    public static void initialiseBot() {
        Reply.printWelcomeMessage();
        Storage.loadProfile();
    }

    /**
     * Terminates the Jarvas bot by saving tasks to storage, printing a goodbye message,
     * and indicating the filename where tasks are saved.
     */
    public static void terminateBot() {
        Storage.saveTasks(tasks);
        Reply.printGoodbyeMessage();
        Reply.printReply("Saved tasks as: " + Constant.FILE_NAME);
    }
}

