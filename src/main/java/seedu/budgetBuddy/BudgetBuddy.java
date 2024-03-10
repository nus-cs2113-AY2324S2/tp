package seedu.budgetBuddy;

import seedu.exception.budgetBuddyException;
public class BudgetBuddy {

    private static final Ui ui = new Ui();
    public static void main(String[] args) throws budgetBuddyException{
        ui.greet();
        String input = ui.readCommand();
        while (true) {
            Parser parser = new Parser();
            input = ui.readCommand();
            parser.parseInput(input);
            ui.showAdd(input);
            if (input.equals("bye")) {
                break;
            }
        }
        ui.showGoodBye();
        ui.closeScanner();
    }
}
