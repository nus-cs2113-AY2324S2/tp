package byteceps;

import byteceps.command.Parser;
import byteceps.ui.Ui;

public class ByteCeps {

    public static void main(String[] args) {

        Ui ui = new Ui();
        Parser parser = new Parser();

        ui.printWelcomeMessage();

        while (!parser.getCommandString().equals("bye")) {
            String userInput = ui.getUserInput();
            parser.parseInput(userInput);

            //prints command and all arguments the user has entered
            System.out.println(parser.toString());
            System.out.println();
        }

        ui.printGoodbyeMessage();
    }
}
