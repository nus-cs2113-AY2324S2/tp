package fitness;

import ui.Ui;

import java.util.Random;

/**
 * Contains methods that execute given the respective commands
 * */
public class FitnessMotivator {

    public static ExerciseList allExercises = new ExerciseList();

    public static final String FILE_PATH = "./data/exerciselist.txt";

    // Required Number of parameters for the fitness add command
    public static final int REQUIRED_NUM_OF_PARAMETERS = 4;

    public FitnessMotivator() {}

    /**
     * Gets one randomised exercise per type, then prints it out.
     *
     * @return A string for printing that lists 5 exercises of different type
     * */
    public String getExercises() {
        Random random = new Random();
        int randomInt1 = random.nextInt(allExercises.size(ExerciseType.ARMS));
        int randomInt2 = random.nextInt(allExercises.size(ExerciseType.CHEST));
        int randomInt3 = random.nextInt(allExercises.size(ExerciseType.ABS));
        int randomInt4 = random.nextInt(allExercises.size(ExerciseType.BACK));
        int randomInt5 = random.nextInt(allExercises.size(ExerciseType.LEGS));

        Exercise exercise1 = allExercises.get(ExerciseType.ARMS, randomInt1);
        Exercise exercise2 = allExercises.get(ExerciseType.CHEST, randomInt2);
        Exercise exercise3 = allExercises.get(ExerciseType.ABS, randomInt3);
        Exercise exercise4 = allExercises.get(ExerciseType.BACK, randomInt4);
        Exercise exercise5 = allExercises.get(ExerciseType.LEGS, randomInt5);
        String message = "These are some of the exercises you can do!" +
                System.lineSeparator() + System.lineSeparator() +
                "1. " + exercise1 + System.lineSeparator() +
                "2. " + exercise2 + System.lineSeparator() +
                "3. " + exercise3 + System.lineSeparator() +
                "4. " + exercise4 + System.lineSeparator() +
                "5. " + exercise5 + System.lineSeparator();

        Ui.printMessageWithSepNewLine(message);
        return message;
    }

    /**
     * Add user created exercise into the existing list
     *
     * @param commandArgs A list of Strings that contain parameters for the Exercise object
     * */
    public void addExercises(String[] commandArgs) {
        assert commandArgs.length == 4 :
            "Something went wrong with parsing fitness add command arguments";

        Exercise newExercise = allExercises.newExercise(commandArgs);
        allExercises.add(newExercise);
        String message = "I have added the following exercise into our list!" +
                System.lineSeparator() + newExercise;
        Ui.printMessageWithSepNewLine(message);
    }

}
