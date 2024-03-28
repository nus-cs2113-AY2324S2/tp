package byteceps;

import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.processing.WorkoutLogsManager;
import byteceps.processing.ExerciseManager;
import byteceps.processing.WorkoutManager;
import byteceps.processing.WeeklyProgramManager;
import byteceps.storage.Storage;
import byteceps.ui.UserInterface;

import java.io.IOException;

public class ByteCeps {
    private static ExerciseManager exerciseManager = null;
    private static WorkoutManager workoutManager = null;
    private static WeeklyProgramManager weeklyProgramManager = null;
    private static WorkoutLogsManager workoutLogsManager = null;
    private static Parser parser;
    private static UserInterface ui;
    private static Storage storage;
    private static final String FILE_PATH = "data.json";

    public ByteCeps() {
        exerciseManager = new ExerciseManager();
        workoutManager = new WorkoutManager(exerciseManager);
        workoutLogsManager = new WorkoutLogsManager();
        weeklyProgramManager = new WeeklyProgramManager(exerciseManager, workoutManager, workoutLogsManager);
        ui = new UserInterface();
        parser = new Parser();
        storage = new Storage(FILE_PATH);
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
                case "program":
                    weeklyProgramManager.execute(parser);
                    continue;
                case "bye":
                case "exit":
                    return;
                default:
                    UserInterface.printMessage("Unknown Command!");
                }

            } catch (Exceptions.ActivityExistsException | Exceptions.ErrorAddingActivity |
                     Exceptions.InvalidInput | Exceptions.ActivityDoesNotExists | IllegalStateException e) {
                UserInterface.printMessage(String.format("Error: %s", e.getMessage()));
            }
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        try {
            storage.load(exerciseManager, workoutManager, weeklyProgramManager, workoutLogsManager);
            commandLine();
            storage.save(exerciseManager, workoutManager, weeklyProgramManager, workoutLogsManager);
        } catch (IOException e) {
            UserInterface.printMessage(String.format("Error: %s", e.getMessage()));
        }
        ui.printGoodbyeMessage();
    }

}
