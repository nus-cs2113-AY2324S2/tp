package seedu.budgetbuddy;

public class BudgetBuddy {

    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser(); 

    public static void main(String[] args) {
        ui.greet();
        String input;
        while (true) {
            input = ui.readCommand(); 
            if (input.equals("bye")) {
                break; 
            }
            parser.parseInput(input); 
            ui.showAdd(input); 
        }
        ui.showGoodBye();
        ui.closeScanner();
    }
}
