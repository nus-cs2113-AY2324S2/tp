package ui;

import utility.Command;
import workouts.Run;

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
     * @throws IllegalArgumentException If an error occurs during command processing.
     */
    public static void processInput() {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String userInput = in.nextLine();

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

                    Output.printHelp();

                    break;

                default:
                    // Yet to implement : throw new CustomException();
                }
            } catch (IllegalArgumentException e) {
                // Yet to implement : Reply.printException(e, Constant.INVALID_COMMAND);
                // Yet to implement : } catch (CustomException e) {
                // Yet to implement : Reply.printException(e);
            }


        }
    }


    /**
     * Constructs a new {@code }  object based on the user input.
     *
     * @param userInput The user input string.
     */
    public static void handleExercise(String userInput){
        // to be implemented
        // If it is a run (help me to abstract it out)
        //Run r1 = new Run("00:10:10", "10.3" );
        //Output.printAddRun(r1);
        //Run r2 = new Run("00:20:10", "20.3", "10/11/2024");
        //Output.printAddRun(r2);
        //Run r3 = new Run("00:30:10", "30.3");
        //Output.printAddRun(r3);
    };
    public static void handleLoad(String userInput){};
    public static void handleNew(String userInput){};
    public static void handleHistory(String userInput){
        Output.printHistory("all");
    };
    public static void handleLatest(String userInput){
        // if asked to show latest run
        Output.printLatestRun();
    };
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
        // Yet to implement : Reply.printWelcomeMessage();
        // Yet to implement : Storage.loadProfile();
    }

    /**
     * Terminates the Jarvas bot by saving tasks to storage, printing a goodbye message,
     * and indicating the filename where tasks are saved.
     */
    public static void terminateBot() {
        // Yet to implement : Storage.saveTasks(tasks);
        // Yet to implement : Reply.printGoodbyeMessage();
        // Yet to implement : Reply.printReply("Saved tasks as: " + Constant.FILE_NAME);
        System.exit(0);
    }
}

