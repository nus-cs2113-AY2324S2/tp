package ui;

import health.HealthList;
import storage.DataFile;
import utility.CustomExceptions;
import utility.ErrorConstant;
import utility.UiConstant;
import utility.HealthConstant;
import utility.WorkoutConstant;
import utility.Command;
import utility.Filters;
import utility.Parser;
import workouts.WorkoutList;
import workouts.Gym;
import workouts.GymStation;
import workouts.Run;
import java.util.Scanner;
import storage.LogFile;
import workouts.Workout;

/**
 * Represents user input parsing and handling
 * before providing feedback to the user.
 */
public class Handler {
    public static Scanner in;
    static LogFile logFile = LogFile.getInstance();

    /**
     * Processes user input and filters for valid command words from enum {@code Command},
     * then creates the relevant object based on details entered.
     *
     * @throws IllegalArgumentException If an error occurs during command processing.
     */
    public static void processInput() {
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            String instruction = userInput.toUpperCase().split(UiConstant.SPLIT_BY_WHITESPACE)[0];
            LogFile.writeLog("User Input: " + userInput, false);

            assert userInput != null : "Object cannot be null";

            try {
                Command command = Command.valueOf(instruction);
                switch (command) {
                case EXIT:
                    System.out.println(UiConstant.EXIT_MESSAGE);
                    return;

                case WORKOUT:
                    handleWorkout(userInput);
                    break;

                case HEALTH:
                    handleHealth(userInput);
                    break;

                case HISTORY:
                    handleHistory(userInput);
                    break;

                case LATEST:
                    handleLatest(userInput);
                    break;

                case DELETE:
                    handleDelete(userInput);
                    break;

                case HELP:
                    Output.printHelp();
                    break;

                default:
                    break; // valueOf results in immediate exception for non-match with enum Command
                }
            } catch (CustomExceptions.InvalidInput e) {
                Output.printException(e.getMessage());
            } catch (IllegalArgumentException e) {
                Output.printException(ErrorConstant.INVALID_COMMAND_ERROR);
            }
        }
    }

    /**
     * Extracts a substring from the given input string based on the provided delimiter.
     *
     * @param input     The input string from which to extract the substring.
     * @param delimiter The delimiter to search for in the input string.
     * @return The extracted substring, or an empty string if the delimiter is not found.
     */
    public static String extractSubstringFromSpecificIndex(String input, String delimiter) {
        int index = input.indexOf(delimiter);
        if (index == -1 || index == input.length() - delimiter.length()) {
            return "";
        }

        int startIndex = index + delimiter.length();
        int endIndex = input.indexOf("/", startIndex);
        if (endIndex == -1) {
            endIndex = input.length();
        }

        return input.substring(startIndex, endIndex).trim();
    }

    /**
     * Constructs either a new Run or Gym object based on the user input.
     *
     * @param userInput The user input string.
     */
    public static void handleWorkout(String userInput) {
        try {
            String typeOfExercise = checkTypeOfExercise(userInput);
            if (typeOfExercise.equals(WorkoutConstant.RUN)) {
                String[] runDetails = Run.getRun(userInput);
                Run newRun = Run.addRun(runDetails);
                Output.printAddRun(newRun);

            } else if (typeOfExercise.equals(WorkoutConstant.GYM)) {
                int numberOfStations = getNumberOfGymStations(userInput);

                String gymDate = getDateFromGym(userInput);
                Gym gym;
                if (gymDate.isEmpty()) {
                    gym = new Gym();
                } else {
                    gym = new Gym(gymDate);
                }
                getGymStation(numberOfStations, gym);
            }
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            Output.printException(e.getMessage());
        }
    }


    /**
     * Handles history command.
     * Show history of all exercises, run or gym.
     *
     * @param userInput The user input string.
     */
    public static void handleHistory(String userInput) {
        String filter = Parser.parseHistoryAndLatestInput(userInput);
        if (filter != null) {
            Output.printHistory(filter);
        }
    }

    /**
     * Handles the delete command.
     * Deletes an item stored within PulsePilot.
     *
     * @param userInput The user input string.
     */
    public static void handleDelete(String userInput) throws CustomExceptions.InvalidInput {
        String[] parsedInputs = Parser.parseDeleteInput(userInput);
        if (parsedInputs == null) {
            return;
        }
        try {
            Filters parsedFilter = Filters.valueOf(parsedInputs[0].toUpperCase());
            int index = Integer.parseInt(parsedInputs[1]) - 1;
            switch (parsedFilter) {
            case BMI:
                HealthList.deleteBmi(index);
                break;

            case PERIOD:
                HealthList.deletePeriod(index);
                break;

            case GYM:
                WorkoutList.deleteGym(index);
                break;

            case RUN:
                WorkoutList.deleteRun(index);
                break;

            default:
                break;
            }
        } catch (CustomExceptions.OutOfBounds e) {
            Output.printException(e.getMessage());
        }
    }

    /**
     * Handles user input related to health data. Parses the user input to determine
     * the type of health data and processes it accordingly.
     *
     * @param userInput A string containing health data information of user.
     */
    public static void handleHealth(String userInput) {
        try {
            String typeOfHealth = extractSubstringFromSpecificIndex(userInput, HealthConstant.HEALTH_FLAG);
            Filters parsedFilter = Filters.valueOf(typeOfHealth.toUpperCase());
            switch(parsedFilter) {
            case BMI:
                Parser.parseBmiInput(userInput);
                break;

            case PERIOD:
                Parser.parsePeriodInput(userInput);
                break;

            case PREDICTION:
                Parser.parsePredictionInput();
                break;

            case APPOINTMENT:
                Parser.parseAppointmentInput(userInput);
                break;

            default:
                break;
            }
        } catch (CustomExceptions.InvalidInput |  CustomExceptions.InsufficientInput e) {
            Output.printException(e.getMessage());
        } catch (IllegalArgumentException e) {
            Output.printException(ErrorConstant.HEALTH_INPUT_BLANK_ERROR);
        }
    }

    /**
     * Retrieves the date from the input for a Gym output.
     * Returns empty string if not specified.
     *
     * @param input The user input string.
     * @return A string representing the date.
     */
    public static String getDateFromGym(String input) {
        try {
            return extractSubstringFromSpecificIndex(input, WorkoutConstant.SPLIT_BY_DATE);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Retrieves the number of gym stations in one Gym object from user input.
     *
     * @param input The user input string.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     * @throws CustomExceptions.InvalidInput      If the user input is invalid or blank.
     */
    //@@author JustinSoh
    public static int getNumberOfGymStations(String input) throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        String numberOfStationString = extractSubstringFromSpecificIndex(input,
                WorkoutConstant.SPLIT_BY_NUMBER_OF_STATIONS);
        assert Integer.parseInt(numberOfStationString) > 0 : ErrorConstant.NEGATIVE_VALUE_ERROR;
        return Integer.parseInt(numberOfStationString);
    }

    //@@author JustinSoh

    /**
     * Retrieves the gym station details and adds a GymStation object to Gym.
     *
     * @param numberOfStations The number of stations in one gym session.
     * @param gym              The Gym object.
     */
    private static void getGymStation(int numberOfStations, Gym gym) {
        int i = 0;
        while (i < numberOfStations) {
            try {

                Output.printGymStationPrompt(i + 1);
                String userInput = in.nextLine();
                GymStation.addGymStationInputValid(gym, userInput);
                i++;
            } catch (CustomExceptions.InsufficientInput | CustomExceptions.InvalidInput e) {
                Output.printException(e.getMessage());
            }
        }
        Output.printAddGym(gym);
    }

    /**
     * Prints the latest run, gym, BMI entry or Period tracked.
     *
     * @param userInput String representing user input.
     */
    public static void handleLatest(String userInput) {
        String filter = Parser.parseHistoryAndLatestInput(userInput);
        if (filter != null) {
            Output.printLatest(filter);
        }
    }

    //@@author

    /**
     * Checks the type of exercise based on the user input.
     * Usage: to use this method whenever the user enters a new exercise.
     * Handles all the checks for input validity and sufficiency.
     * Can assume input is valid and sufficient if no exceptions are thrown.
     *
     * @param userInput The user input string.
     * @return The type of exercise {@code Constant.RUN} or {@code Constant.GYM}.
     * @throws CustomExceptions.InvalidInput      If the user input is invalid or blank.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     */
    public static String checkTypeOfExercise(String userInput) throws
            CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {

        boolean exerciseTypeIsValid = false;
        boolean isRunValid = false;
        boolean isGymValid = false;

        String exerciseType = extractSubstringFromSpecificIndex(userInput, WorkoutConstant.SPLIT_BY_EXERCISE_TYPE);

        exerciseTypeIsValid = Workout.checkIfExerciseTypeIsValid(exerciseType);
        boolean isRun = exerciseType.equals(WorkoutConstant.RUN);
        boolean isGym = exerciseType.equals(WorkoutConstant.GYM);

        if (isRun) {
            String runDistance = extractSubstringFromSpecificIndex(userInput, WorkoutConstant.SPLIT_BY_DISTANCE);
            String runTime = extractSubstringFromSpecificIndex(userInput, WorkoutConstant.SPLIT_BY_TIME);
            String runDate = extractSubstringFromSpecificIndex(userInput, WorkoutConstant.SPLIT_BY_DATE);
            isRunValid = Run.checkIfRunIsValid(runDistance, runTime, runDate);
        } else if (isGym) {
            String numberOfStations = extractSubstringFromSpecificIndex(userInput,
                    WorkoutConstant.SPLIT_BY_NUMBER_OF_STATIONS);
            isGymValid = Gym.checkIfGymIsValid(numberOfStations);
        }


        if (exerciseTypeIsValid && isRunValid) {
            return WorkoutConstant.RUN;
        } else if (exerciseTypeIsValid && isGymValid) {
            return WorkoutConstant.GYM;
        } else {
            return "";
        }

    }

    /**
     * Get user's name, and print profile induction messages.
     */
    public static void userInduction() {
        String name = in.nextLine();
        System.out.println("Welcome aboard, Captain " + name);
        Output.printLine();

        System.out.println("Tips: Enter 'help' to view the pilot manual!");
        System.out.println("Initiating FTL jump sequence...");

        // DataFile.saveName(name);
        LogFile.writeLog("Name Entered: " + name, false);

        System.out.println("FTL jump completed.");
    }

    /**
     * Initialise scanner to read user input.
     */
    public static void initialiseScanner() {
        in = new Scanner(System.in);
        assert in != null : "Object cannot be null";
    }

    /**
     * Close scanner to stop reading user input.
     */
    public static void destroyScanner() {
        if (in != null) {
            in.close();
        }
    }

    /**
     * Initializes PulsePilot by printing a welcome message, loading tasks from storage,
     * and returning the tasks list.
     */
    public static void initialiseBot() {
        Output.printWelcomeBanner();
        initialiseScanner();
        LogFile.writeLog("Started bot", false);

        int status = DataFile.loadDataFile();
        //String name = DataFile.loadName();
        Output.printGreeting(status, "name");

        if (status == 1) {
            userInduction();
        }
        System.out.println("Terminal primed. Command inputs are now accepted...");
        Output.printLine();
    }

    /**
     * Terminates PulsePilot by saving tasks to storage, printing a goodbye message,
     * and indicating the filename where tasks are saved.
     */
    public static void terminateBot() {
        // Yet to implement : Storage.saveTasks(tasks);
        Output.printGoodbyeMessage();
        // Yet to implement : Reply.printReply("Saved tasks as: " + Constant.FILE_NAME);
        LogFile.writeLog("Bot exited gracefully", false);
        destroyScanner();
        System.exit(0);
    }
}
