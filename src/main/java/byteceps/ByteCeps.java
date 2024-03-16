package byteceps;

import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.processing.ExerciseManager;
import byteceps.ui.UserInterface;

public class ByteCeps {
    private static ExerciseManager exerciseManager = null;
    private static Parser parser;
    private static UserInterface ui;

    public ByteCeps() {
        exerciseManager = new ExerciseManager();
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
                case "bye":
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command!");
                }

            } catch (Exceptions.ActivityExistsException | Exceptions.ErrorAddingActivity |
                     Exceptions.InvalidInput | Exceptions.ActivityDoesNotExists e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        commandLine();
        ui.printGoodbyeMessage();
    }


}
