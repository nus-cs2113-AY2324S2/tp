package fitness;

import ui.Ui;

import java.util.Random;

public class FitnessMotivator {

    public static ExerciseList allExercises = new ExerciseList();

    public static final String FILE_PATH = "./data/exerciselist.txt";

    public FitnessMotivator() {}

    public String getExercises() {
        Random random = new Random();
        int randomInt1 = random.nextInt(allExercises.size("Arms"));
        int randomInt2 = random.nextInt(allExercises.size("Chest"));
        int randomInt3 = random.nextInt(allExercises.size("Abs"));
        int randomInt4 = random.nextInt(allExercises.size("Back"));
        int randomInt5 = random.nextInt(allExercises.size("Legs"));

        Exercise exercise1 = allExercises.get("Arms", randomInt1);
        Exercise exercise2 = allExercises.get("Chest", randomInt2);
        Exercise exercise3 = allExercises.get("Abs", randomInt3);
        Exercise exercise4 = allExercises.get("Back", randomInt4);
        Exercise exercise5 = allExercises.get("Legs", randomInt5);
        String message = "These are some of the exercises you can do!\n\n" +
                "1. " + exercise1 + "\n" + "2. " + exercise2 + "\n" + "3. " + exercise3 + "\n" +
                "4. " + exercise4 + "\n" + "5. " + exercise5 + "\n";

        Ui.printMessageWithSepNewLine(message);
        return message;
    }

}
