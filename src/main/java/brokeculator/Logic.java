package brokeculator;
import brokeculator.expense.ExpenseManager;
import brokeculator.frontend.UI;
import brokeculator.storage.FileManager;
import brokeculator.command.Command;
import brokeculator.storage.parsing.GeneralFileParser;
import brokeculator.parser.GeneralInputParser;

public class Logic {
    private FileManager fileManager;
    private ExpenseManager expenseManager;
    public Logic(FileManager fileManager, ExpenseManager expenseManager) {
        this.fileManager = fileManager;
        this.expenseManager = expenseManager;
    }
    public void run() {
        loadExpensesFromFile();
        while (true) {
            try {
                UI.print("Enter a command:");
                String userInput = UI.getUserInput();
                assert userInput != null;
                Command command = GeneralInputParser.getCommandFromUserInput(userInput);
                command.execute(expenseManager);
                saveExpensesToFile();
            } catch (Exception e) {
                UI.print("error occurred, sus. " + e.getMessage());
            }
        }
    }
    private void loadExpensesFromFile() {
        boolean hasNoFileErrors = fileManager.openFile();
        if (!hasNoFileErrors) {
            UI.print("continuing without file");
            return;
        }
        while (fileManager.hasNextLine()) {
            String line = fileManager.readNextLine();
            Command loadCommand = GeneralFileParser.getCommandFromFileInput(line);
            loadCommand.execute(expenseManager);
        }
        //after obtaining a clean expense list, we save it back to the file to remove any corrupted data
        saveExpensesToFile();
    }
    private void saveExpensesToFile() {
        try {
            String expenseListToSave = expenseManager.getExpensesStringRepresentation();
            fileManager.save(expenseListToSave);
        } catch (Exception e) {
            UI.print("file save error occurred" + e.getMessage());
        }
    }
}
