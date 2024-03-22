package brokeculator;
import brokeculator.dashboard.Dashboard;
import brokeculator.exceptions.BrokeculatorException;
import brokeculator.frontend.UI;
import brokeculator.command.Command;
import brokeculator.storage.parsing.GeneralFileParser;
import brokeculator.parser.GeneralInputParser;

public class Logic {
    private final Dashboard dashboard;
    public Logic(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
    public void run() {
        loadExpensesFromFile();
        UI.greetUser();
        while (true) {
            try {
                String userInput = UI.getUserInput();
                assert userInput != null;
                Command command = GeneralInputParser.getCommandFromUserInput(userInput);
                assert command != null : "command should not be null";
                command.execute(dashboard);
                saveExpensesToFile();
            } catch (BrokeculatorException b) {
                UI.prettyPrint("Brokeculator error occurred. " + b.getMessage());
            } catch (Exception e) {
                UI.prettyPrint("Exception caught in main loop. " + e.getMessage());
            }
        }
    }
    private void loadExpensesFromFile() {
        boolean hasNoFileErrors = dashboard.getFileManager().openFile();
        if (!hasNoFileErrors) {
            UI.println("continuing without file");
            return;
        }
        while (dashboard.getFileManager().hasNextLine()) {
            String line = dashboard.getFileManager().readNextLine();
            Command loadCommand = GeneralFileParser.getCommandFromFileInput(line);
            loadCommand.execute(dashboard);
        }
        //after obtaining a clean expense list, we save it back to the file to remove any corrupted data
        saveExpensesToFile();
    }
    private void saveExpensesToFile() {
        try {
            String expenseListToSave = dashboard.getExpenseManager().getExpensesStringRepresentation();
            dashboard.getFileManager().save(expenseListToSave);
        } catch (Exception e) {
            UI.prettyPrint("file save error occurred" + e.getMessage());
        }
    }
}
