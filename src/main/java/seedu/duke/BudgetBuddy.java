package seedu.duke;

import java.util.Scanner;

public class BudgetBuddy {

    private Ui ui;
    private Parser parser;

    public void handleCommands(String input) {
        Command command = parser.parseCommand(input);

        if (command != null) {
            command.execute();
        } else {
            System.out.println("Invalid Command");
        }
    }
    public BudgetBuddy() {
        ui = new Ui();
        parser = new Parser();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();

            if (parser.isExitCommand(input)) {
                isExit = true;
            } else {
                handleCommands(input);
            }

        }

        ui.showGoodbye();
        scanner.close();
    }

    public static void main(String[] args) {
        new BudgetBuddy().run();
    }
}
