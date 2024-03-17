package byteceps;

import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.processing.ExerciseManager;
import byteceps.processing.WorkoutManager;
import byteceps.ui.UserInterface;

public class ByteCeps {
    private static ExerciseManager exerciseManager = null;
    private static WorkoutManager workoutManager = null;
    private static Parser parser;
    private static UserInterface ui;

    public ByteCeps() {
        exerciseManager = new ExerciseManager();
        workoutManager = new WorkoutManager();
        ui = new UserInterface();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new ByteCeps().run();
    }

    public void commandLine() {
        while (true) {
            String userInput = ui.getUserInput();
            parser.parseInput(userInput);

            try {
                switch (parser.getCommand()) {
                case "exercise":
                    exerciseManager.execute(parser);
                    continue;
                case "workout":
                    workoutManager.execute(parser);
                    continue;
                case "bye":
                case "exit":
                    return;
                default:
                    UserInterface.printMessage("Unknown Command!");
                }

            } catch (Exceptions.ActivityExistsException | Exceptions.ErrorAddingActivity |
                     Exceptions.InvalidInput | Exceptions.ActivityDoesNotExists e) {
                UserInterface.printMessage(String.format("[BYTECEPS]> Error: %s", e.getMessage()));
            }
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        commandLine();
        ui.printGoodbyeMessage();
    }


}
