package seedu.budgetBuddy;

public class BudgetBuddy {

    private static final Ui ui = new Ui();
    public static void main(String[] args){
        ui.greet();
        String input = ui.readCommand();
        while (true) {
            if (input.equals("bye")) {
                break;
            } else{ 
                Parser parser = new Parser();
                input = ui.readCommand();
                parser.parseInput(input);
                ui.showAdd(input);
            }
        }
        ui.showGoodBye();
        ui.closeScanner();
    }
}
