package byteceps;

import byteceps.commands.Command;
import byteceps.commands.CommandResult;
import byteceps.commands.Parser;
import byteceps.ui.Ui;

import java.util.ArrayList;

public class ByteCeps {
    private final ArrayList<Object> exerciseList = new ArrayList<>();
    private final ArrayList<Object> workoutsList = new ArrayList<>();
    private final ArrayList<Object> plansList = new ArrayList<>();


    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        ui.printWelcomeMessage();


        while (true) {
            try {
                String userInput = ui.getUserInput();
                parser.parseInput(userInput);

                Command command = parser.parseCommand();
                CommandResult commandOutput = command.execute();
                System.out.println(commandOutput.feedbackToUser);

                if (commandOutput.feedbackToUser.equals("exit")) {
                    break;
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        ui.printGoodbyeMessage();
    }
}
