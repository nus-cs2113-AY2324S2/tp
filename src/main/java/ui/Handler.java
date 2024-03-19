package ui;

import health.Bmi;
import health.Health;
import health.HealthList;
import health.Period;
import utility.Command;
import utility.Constant;
import utility.CustomExceptions;
import workouts.Gym;
import workouts.Run;

import java.util.Scanner;

import storage.LogFile;


/**
 * Represents user input parsing and handling
 * before providing feedback to the user.
 */
public class Handler {
    public static Scanner in;
    LogFile logFile = LogFile.getInstance();

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
            String instruction = userInput.toUpperCase().split(" ")[0];
            LogFile.writeLog("User Input: " + userInput, false);

            try {
                Command command = Command.valueOf(instruction);
                switch (command) {

                case EXIT:
                    return;
                case NEW:

                    handleExercise(userInput);

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
                case HELP:

                    Output.printHelp();

                    break;

                default:

                    break; // valueOf results in immediate exception for non-match with enum Command
                }
            } catch (IllegalArgumentException e) {
                Output.printException(e, Constant.INVALID_COMMAND);
                // Yet to implement : } catch (CustomException e) {
                // Yet to implement : Reply.printException(e);

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
     * Constructs a new {@code }  object based on the user input.
     *
     * @param userInput The user input string.
     */
    public static void handleExercise(String userInput) {
        try {
            String typeOfExercise = checkTypeOfExercise(userInput);
            if (typeOfExercise.equals(Constant.RUN)) {
                String[] runDetails = Run.getRun(userInput);
                if (runDetails[0].isEmpty() || runDetails[1].isEmpty() || runDetails[2].isEmpty()
                        || runDetails[3].isEmpty()) {
                    throw new CustomExceptions.InvalidInput("Missing parameter(s)");
                }

                new Run(runDetails[2], runDetails[1], runDetails[3]);
                System.out.println("Added: run | " + runDetails[1] + " | " + runDetails[2] + " | " + runDetails[3]);

            } else if (typeOfExercise.equals(Constant.GYM)) {
                // Yet to implement : handleGym(userInput);
                int numberOfStations = getNumberOfGymStations(userInput);
                Gym gym = new Gym();
                getGymStation(numberOfStations, gym);
            }
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            System.out.println(e.getMessage());
            // throw new CustomExceptions.InvalidInput(Constant.UNSPECIFIED_PARAMETER);
        }

    }


    /**
     * Handle history command.
     * Expected command: `history /e:[all\run\gym]`
     * Show history of all exercises, run or gym.
     * @param userInput
     */
    public static void handleHistory(String userInput) {
        // if asked to show history
        String [] inputs = userInput.split(Constant.SPLIT_BY_SLASH);
        String filter = inputs[1].split(":")[1];
        Output.printHistory(filter);

    }


    /**
     * Handles user input related to health data. Parses the user input to determine
     * the type of health data and processes it accordingly.
     *
     * @param userInput A string containing health data information of user.
     */
    public static void handleHealth(String userInput){
        try {
            String typeOfHealth = Health.checkTypeOfHealth(userInput);
            if (typeOfHealth.equals(Constant.BMI)) {
                String[] bmiDetails = Bmi.getBmi(userInput);

                if (bmiDetails[0].isEmpty()
                        || bmiDetails[1].isEmpty()
                        || bmiDetails[2].isEmpty()
                        || bmiDetails[3].isEmpty()) {
                    throw new CustomExceptions.InvalidInput(Constant.MISSING_PARAMETERS);
                }
                Bmi newBmi = new Bmi(bmiDetails[1], bmiDetails[2], bmiDetails[3]);
                HealthList.addBmi(newBmi);
                System.out.println(Constant.BMI_ADDED_MESSAGE_PREFIX
                        + bmiDetails[1]
                        + Constant.LINE
                        + bmiDetails[2]
                        + Constant.LINE
                        + bmiDetails[3]);
                System.out.println(newBmi);
            } else if (typeOfHealth.equals(Constant.PERIOD)){
                String[] periodDetails = Period.getPeriod(userInput);

                if (periodDetails[0].isEmpty() || periodDetails[1].isEmpty() || periodDetails[2].isEmpty()) {
                    throw new CustomExceptions.InvalidInput(Constant.MISSING_PARAMETERS);
                }
                Period newPeriod = new Period(periodDetails[1], periodDetails[2]);
                HealthList.addPeriod(newPeriod);
                System.out.println("Added: period | " + periodDetails[1] + " | " + periodDetails[2]);
                System.out.println(newPeriod);
            }
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Called when a user inputs a new gym
     * Creates a gym object and prompts the user to enter details of the gym workouts and adds that to the gym object .
     *
     * @param input The user input string.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     * @throws CustomExceptions.InvalidInput      If the user input is invalid or blank.
     */
    public static void getGym(String input) throws CustomExceptions.InsufficientInput, CustomExceptions.InvalidInput {
        // input that I will get is something like new /e: gym /n: 2
        // use get index method that alfattih will push later
        int numberOfStations = 2; // assuming i parsed the user initial input
        Gym gym = new Gym();
        for (int i = 0; i < numberOfStations; i++) {
            Output.printGymStationPrompt(i + 1);
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            checkAndAddGymStationInput(userInput, gym);
        }
    }

    private static void checkAndAddGymStationInput(String userInput, Gym gym) throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        // probably change to alfa method
        String[] inputs = userInput.split(Constant.SPLIT_BY_SLASH); // Constant.SPLIT_BY_SLASH = "/"

        if (inputs.length != Constant.LENGTH_OF_GYM_STATION_INPUTS) {
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_GYM_STATION);
        }

        if (inputs[Constant.INDEX_OF_STATION_NAME].isBlank() ||
                inputs[Constant.INDEX_OF_STATION_SETS].isBlank() ||
                inputs[Constant.INDEX_OF_STATION_REPS].isBlank() ||
                inputs[Constant.INDEX_OF_STATION_WEIGHTS].isBlank()) {
            throw new CustomExceptions.InvalidInput(Constant.BLANK_INPUT_FOR_GYM_STATION);
        }

        String exerciseName = inputs[Constant.INDEX_OF_STATION_NAME].trim();

        try {

            int numberOfSets = Integer.parseInt(inputs[Constant.INDEX_OF_STATION_SETS]);
            int repetition = Integer.parseInt(inputs[Constant.INDEX_OF_STATION_REPS]);
            int weights = Integer.parseInt(inputs[Constant.INDEX_OF_STATION_WEIGHTS]);
            gym.addStation(exerciseName, weights, numberOfSets, repetition);
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(Constant.NUMERIC_INPUT_REQUIRED_GYM_STATION);
        }
    }

    /**
     * Called when a user inputs a new gym
     * Creates a gym object and prompts the user to enter details of the gym workouts and adds that to the gym object .
     *
     * @param input The user input string.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     * @throws CustomExceptions.InvalidInput      If the user input is invalid or blank.
     */
    public static int getNumberOfGymStations(String input) throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        // input that I will get is something like new /e:gym /n: 2
        // use get index method that alfattih will push later

        String [] inputs = input.split(Constant.SPLIT_BY_SLASH);
        String numberOfStationsString = inputs[2];
        String numberOfStationStr =  numberOfStationsString.split(":")[1];
        System.out.println(numberOfStationsString);
        return Integer.parseInt(numberOfStationStr);

    }

    private static void getGymStation(int numberOfStations, Gym gym) {
        try{
            for (int i = 0; i < numberOfStations; i++) {
                Output.printGymStationPrompt(i + 1);
                String userInput = in.nextLine();
                String[] inputs = userInput.split(Constant.SPLIT_BY_SLASH);
                String[] validatedInputs = checkGymStationInput(inputs);
                addGymStationInput(validatedInputs, gym);
            }
            Output.printAddGym(gym);
        } catch (CustomExceptions.InsufficientInput | CustomExceptions.InvalidInput e) {
            System.out.println(e.getMessage());
        }
    }
    public static void handleLatest(String userInput){
        // if asked to show latest run
        Output.printLatestRun();
    }

    private static String[] checkGymStationInput(String[] inputs) throws
            CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {


        String exerciseName = inputs[Constant.INDEX_OF_STATION_NAME].trim();
        String sets = inputs[Constant.INDEX_OF_STATION_SETS].split(":")[1].trim();
        String reps = inputs[Constant.INDEX_OF_STATION_REPS].split(":")[1].trim();
        String weights = inputs[Constant.INDEX_OF_STATION_WEIGHTS].split(":")[1].trim();

        if (exerciseName.isBlank() || sets.isBlank() || reps.isBlank() || weights.isBlank()) {
            throw new CustomExceptions.InvalidInput(Constant.BLANK_INPUT_FOR_GYM_STATION);
        }
        try {
            Integer.parseInt(sets);
            Integer.parseInt(reps);
            Integer.parseInt(weights);
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(Constant.NUMERIC_INPUT_REQUIRED_GYM_STATION);
        }

        return new String[]{exerciseName, sets, reps, weights};
    }

    private static void addGymStationInput(String [] validatedInputs, Gym gym) throws
            CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {

        String exerciseName = validatedInputs[Constant.INDEX_OF_STATION_NAME];
        int weights = Integer.parseInt(validatedInputs[Constant.INDEX_OF_STATION_WEIGHTS]);
        int numberOfSets = Integer.parseInt(validatedInputs[Constant.INDEX_OF_STATION_SETS]);
        int repetition = Integer.parseInt(validatedInputs[Constant.INDEX_OF_STATION_REPS]);
        gym.addStation(exerciseName, weights, numberOfSets, repetition);
    }

    /**
     * Checks the type of exercise based on the user input.
     * Usage: to use this method whenever the user enters a new exercise.
     * Handles all the checks for input validity and sufficiency.
     * Can assume input is valid and sufficient if no exceptions are thrown.
     * @param userInput The user input string.
     * @return The type of exercise {@code Constant.RUN} or {@code Constant.GYM}.
     * @throws CustomExceptions.InvalidInput If the user input is invalid or blank.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     */
    public static String checkTypeOfExercise(String userInput) throws
            CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String[] userInputs = userInput.split("/"); // Constant.SPLIT_BY_SLASH = "/"

        String exerciseType = userInputs[Constant.EXERCISE_TYPE_INDEX].trim(); // Constant.EXERCISE_TYPE_INDEX = 1

        if (exerciseType.isBlank()){
            throw new CustomExceptions.InvalidInput(Constant.BLANK_INPUT_FOR_EXERCISE);
        }

        exerciseType = exerciseType.toLowerCase();


        boolean isRun = exerciseType.equals(Constant.RUN_INPUT);
        boolean isGym = exerciseType.equals(Constant.GYM_INPUT);
        if(!isRun && !isGym){
            throw new CustomExceptions.InvalidInput(Constant.INVALID_INPUT_FOR_EXERCISE);
        }


        if (isRun && userInputs.length < 5) {
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_RUN);
        }

        if (isGym && userInputs.length < 3) {
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_GYM);
        }


        if (isRun){
            return Constant.RUN;
        } else {
            return Constant.GYM;
        }
    }


    /**
     * Initializes PulsePilot by printing a welcome message, loading tasks from storage,
     * and returning the tasks list.
     */
    public static void initialiseBot() {
        Output.printWelcomeBanner();
        LogFile.writeLog("Started bot", false);
        // Yet to implement : Check for existing save, if not, make a new one
        // Yet to implement : int status = Storage.load();
        int status = 1;
        if (status == 1) {
            Output.printGreeting(1);
            Scanner in = new Scanner(System.in);
            String name = in.nextLine();
            System.out.println("Welcome aboard, " + name);
            LogFile.writeLog("Name entered: " + name, false);
        }
    }

    /**
     * Terminates PulsePilot by saving tasks to storage, printing a goodbye message,
     * and indicating the filename where tasks are saved.
     */
    public static void terminateBot() {
        LogFile.writeLog("Bot exited gracefully", false);
        // Yet to implement : Storage.saveTasks(tasks);
        // Yet to implement : Reply.printGoodbyeMessage();
        // Yet to implement : Reply.printReply("Saved tasks as: " + Constant.FILE_NAME);
        System.exit(0);
    }
}

=======
package ui;

import health.Bmi;
import health.Health;
import health.HealthList;
import health.Period;
import utility.Command;
import utility.Constant;
import utility.CustomExceptions;
import workouts.Gym;
import workouts.Run;

import java.util.Scanner;

import storage.LogFile;


/**
 * Represents user input parsing and handling
 * before providing feedback to the user.
 */
public class Handler {
    public static Scanner in;
    LogFile logFile = LogFile.getInstance();

    /**
     * Processes user input and filters for valid command words from enum {@code Command},
     * then creates the relevant {@code Task} object based on details entered.
     *
     * @throws IllegalArgumentException If an error occurs during command processing.
     */

    public static void processInput() throws CustomExceptions.InvalidInput {
        Scanner in = new Scanner(System.in);


        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            String instruction = userInput.toUpperCase().split(" ")[0];
            LogFile.writeLog("User Input: " + userInput, false);

            try {
                Command command = Command.valueOf(instruction);
                switch (command) {

                case EXIT:
                    return;
                case NEW:

                    handleExercise(userInput);

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
                case HELP:

                    Output.printHelp();

                    break;

                default:

                    break; // valueOf results in immediate exception for non-match with enum Command
                }
            } catch (IllegalArgumentException e) {
                Output.printException(e, Constant.INVALID_COMMAND);
                // Yet to implement : } catch (CustomException e) {
                // Yet to implement : Reply.printException(e);

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
     * Constructs a new {@code }  object based on the user input.
     *
     * @param userInput The user input string.
     */
    public static void handleExercise(String userInput) {
        try {
            String typeOfExercise = checkTypeOfExercise(userInput);
            if (typeOfExercise.equals(Constant.RUN)) {
                String[] runDetails = Run.getRun(userInput);
                if (runDetails[0].isEmpty() || runDetails[1].isEmpty() || runDetails[2].isEmpty()
                        || runDetails[3].isEmpty()) {
                    throw new CustomExceptions.InvalidInput("Missing parameter(s)");
                }

                new Run(runDetails[2], runDetails[1], runDetails[3]);
                System.out.println("Added: run | " + runDetails[1] + " | " + runDetails[2] + " | " + runDetails[3]);

            } else if (typeOfExercise.equals(Constant.GYM)) {
                // Yet to implement : handleGym(userInput);
                int numberOfStations = getNumberOfGymStations(userInput);
                Gym gym = new Gym();
                getGymStation(numberOfStations, gym);
            }
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            System.out.println(e.getMessage());
            // throw new CustomExceptions.InvalidInput(Constant.UNSPECIFIED_PARAMETER);
        }

    }


    /**
     * Handle history command.
     * Expected command: `history /e:[all\run\gym]`
     * Show history of all exercises, run or gym.
     * @param userInput
     */
    public static void handleHistory(String userInput) {
        // if asked to show history
        String [] inputs = userInput.split(Constant.SPLIT_BY_SLASH);
        String filter = inputs[1].split(":")[1];
        Output.printHistory(filter);

    }


    /**
     * Handles user input related to health data. Parses the user input to determine
     * the type of health data and processes it accordingly.
     *
     * @param userInput A string containing health data information of user.
     */
    public static void handleHealth(String userInput){
        try {
            String typeOfHealth = Health.checkTypeOfHealth(userInput);
            if (typeOfHealth.equals(Constant.BMI)){
                String[] bmiDetails = Bmi.getBmi(userInput);

                if (bmiDetails[0].isEmpty()
                        || bmiDetails[1].isEmpty()
                        || bmiDetails[2].isEmpty()
                        || bmiDetails[3].isEmpty()) {
                    throw new CustomExceptions.InvalidInput(Constant.MISSING_PARAMETERS);
                }
                Bmi newBmi = new Bmi(bmiDetails[1], bmiDetails[2], bmiDetails[3]);
                HealthList.addBmi(newBmi);
                System.out.println(Constant.BMI_ADDED_MESSAGE_PREFIX
                        + bmiDetails[1]
                        + Constant.LINE
                        + bmiDetails[2]
                        + Constant.LINE
                        + bmiDetails[3]);
                System.out.println(newBmi);
            } else if (typeOfHealth.equals(Constant.PERIOD)){
                String[] periodDetails = Period.getPeriod(userInput);

                if (periodDetails[0].isEmpty() || periodDetails[1].isEmpty() || periodDetails[2].isEmpty()) {
                    throw new CustomExceptions.InvalidInput(Constant.MISSING_PARAMETERS);
                }
                Period newPeriod = new Period(periodDetails[1], periodDetails[2]);
                HealthList.addPeriod(newPeriod);
                System.out.println(Constant.PERIOD_ADDED_MESSAGE_PREFIX
                        + periodDetails[1]
                        + Constant.LINE
                        + periodDetails[2]);
                System.out.println(newPeriod);
            }
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Called when a user inputs a new gym
     * Creates a gym object and prompts the user to enter details of the gym workouts and adds that to the gym object .
     *
     * @param input The user input string.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     * @throws CustomExceptions.InvalidInput      If the user input is invalid or blank.
     */
    public static void getGym(String input) throws CustomExceptions.InsufficientInput, CustomExceptions.InvalidInput {
        // input that I will get is something like new /e: gym /n: 2
        // use get index method that alfattih will push later
        int numberOfStations = 2; // assuming i parsed the user initial input
        Gym gym = new Gym();
        for (int i = 0; i < numberOfStations; i++) {
            Output.printGymStationPrompt(i + 1);
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            checkAndAddGymStationInput(userInput, gym);
        }
    }

    private static void checkAndAddGymStationInput(String userInput, Gym gym) throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        // probably change to alfa method
        String[] inputs = userInput.split(Constant.SPLIT_BY_SLASH); // Constant.SPLIT_BY_SLASH = "/"

        if (inputs.length != Constant.LENGTH_OF_GYM_STATION_INPUTS) {
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_GYM_STATION);
        }

        if (inputs[Constant.INDEX_OF_STATION_NAME].isBlank() ||
                inputs[Constant.INDEX_OF_STATION_SETS].isBlank() ||
                inputs[Constant.INDEX_OF_STATION_REPS].isBlank() ||
                inputs[Constant.INDEX_OF_STATION_WEIGHTS].isBlank()) {
            throw new CustomExceptions.InvalidInput(Constant.BLANK_INPUT_FOR_GYM_STATION);
        }

        String exerciseName = inputs[Constant.INDEX_OF_STATION_NAME].trim();

        try {

            int numberOfSets = Integer.parseInt(inputs[Constant.INDEX_OF_STATION_SETS]);
            int repetition = Integer.parseInt(inputs[Constant.INDEX_OF_STATION_REPS]);
            int weights = Integer.parseInt(inputs[Constant.INDEX_OF_STATION_WEIGHTS]);
            gym.addStation(exerciseName, weights, numberOfSets, repetition);
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(Constant.NUMERIC_INPUT_REQUIRED_GYM_STATION);
        }
    }

    /**
     * Called when a user inputs a new gym
     * Creates a gym object and prompts the user to enter details of the gym workouts and adds that to the gym object .
     *
     * @param input The user input string.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     * @throws CustomExceptions.InvalidInput      If the user input is invalid or blank.
     */
    public static int getNumberOfGymStations(String input) throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        // input that I will get is something like new /e:gym /n: 2
        // use get index method that alfattih will push later

        String [] inputs = input.split(Constant.SPLIT_BY_SLASH);
        String numberOfStationsString = inputs[2];
        String numberOfStationStr =  numberOfStationsString.split(":")[1];
        System.out.println(numberOfStationsString);
        return Integer.parseInt(numberOfStationStr);

    }

    private static void getGymStation(int numberOfStations, Gym gym) {
        try{
            for (int i = 0; i < numberOfStations; i++) {
                Output.printGymStationPrompt(i + 1);
                String userInput = in.nextLine();
                String[] inputs = userInput.split(Constant.SPLIT_BY_SLASH);
                String[] validatedInputs = checkGymStationInput(inputs);
                addGymStationInput(validatedInputs, gym);
            }
            Output.printAddGym(gym);
        } catch (CustomExceptions.InsufficientInput | CustomExceptions.InvalidInput e) {
            System.out.println(e.getMessage());
        }
    }
    public static void handleLatest(String userInput){
        // if asked to show latest run
        Output.printLatestRun();
    }

    private static String[] checkGymStationInput(String[] inputs) throws
            CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {


        String exerciseName = inputs[Constant.INDEX_OF_STATION_NAME].trim();
        String sets = inputs[Constant.INDEX_OF_STATION_SETS].split(":")[1].trim();
        String reps = inputs[Constant.INDEX_OF_STATION_REPS].split(":")[1].trim();
        String weights = inputs[Constant.INDEX_OF_STATION_WEIGHTS].split(":")[1].trim();

        if (exerciseName.isBlank() || sets.isBlank() || reps.isBlank() || weights.isBlank()) {
            throw new CustomExceptions.InvalidInput(Constant.BLANK_INPUT_FOR_GYM_STATION);
        }
        try {
            Integer.parseInt(sets);
            Integer.parseInt(reps);
            Integer.parseInt(weights);
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(Constant.NUMERIC_INPUT_REQUIRED_GYM_STATION);
        }

        return new String[]{exerciseName, sets, reps, weights};
    }

    private static void addGymStationInput(String [] validatedInputs, Gym gym) throws
            CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {

        String exerciseName = validatedInputs[Constant.INDEX_OF_STATION_NAME];
        int weights = Integer.parseInt(validatedInputs[Constant.INDEX_OF_STATION_WEIGHTS]);
        int numberOfSets = Integer.parseInt(validatedInputs[Constant.INDEX_OF_STATION_SETS]);
        int repetition = Integer.parseInt(validatedInputs[Constant.INDEX_OF_STATION_REPS]);
        gym.addStation(exerciseName, weights, numberOfSets, repetition);
    }

    /**
     * Checks the type of exercise based on the user input.
     * Usage: to use this method whenever the user enters a new exercise.
     * Handles all the checks for input validity and sufficiency.
     * Can assume input is valid and sufficient if no exceptions are thrown.
     * @param userInput The user input string.
     * @return The type of exercise {@code Constant.RUN} or {@code Constant.GYM}.
     * @throws CustomExceptions.InvalidInput If the user input is invalid or blank.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     */
    public static String checkTypeOfExercise(String userInput) throws
            CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String[] userInputs = userInput.split("/"); // Constant.SPLIT_BY_SLASH = "/"

        String exerciseType = userInputs[Constant.EXERCISE_TYPE_INDEX].trim(); // Constant.EXERCISE_TYPE_INDEX = 1

        if (exerciseType.isBlank()){
            throw new CustomExceptions.InvalidInput(Constant.BLANK_INPUT_FOR_EXERCISE);
        }

        exerciseType = exerciseType.toLowerCase();


        boolean isRun = exerciseType.equals(Constant.RUN_INPUT);
        boolean isGym = exerciseType.equals(Constant.GYM_INPUT);
        if(!isRun && !isGym){
            throw new CustomExceptions.InvalidInput(Constant.INVALID_INPUT_FOR_EXERCISE);
        }


        if (isRun && userInputs.length < 5) {
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_RUN);
        }

        if (isGym && userInputs.length < 3) {
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_GYM);
        }


        if (isRun){
            return Constant.RUN;
        } else {
            return Constant.GYM;
        }
    }


    /**
     * Initializes PulsePilot by printing a welcome message, loading tasks from storage,
     * and returning the tasks list.
     */
    public static void initialiseBot() {
        Output.printWelcomeBanner();
        LogFile.writeLog("Started bot", false);
        // Yet to implement : Check for existing save, if not, make a new one
        // Yet to implement : int status = Storage.load();
        int status = 1;
        if (status == 1) {
            Output.printGreeting(1);
            Scanner in = new Scanner(System.in);
            String name = in.nextLine();
            System.out.println("Welcome aboard, " + name);
            LogFile.writeLog("Name entered: " + name, false);
        }
    }

    /**
     * Terminates PulsePilot by saving tasks to storage, printing a goodbye message,
     * and indicating the filename where tasks are saved.
     */
    public static void terminateBot() {
        LogFile.writeLog("Bot exited gracefully", false);
        // Yet to implement : Storage.saveTasks(tasks);
        // Yet to implement : Reply.printGoodbyeMessage();
        // Yet to implement : Reply.printReply("Saved tasks as: " + Constant.FILE_NAME);
        System.exit(0);
    }
}

