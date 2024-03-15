package seedu.brokeculator;

public class Logic {
    public Logic() {
        GeneralInputParser mainParser = new GeneralInputParser();
        FileManager fileManager = new FileManager();
        ExpenseManager expenseManager = new ExpenseManager();
    }
    public void run() {
        while (true) {
            try {
                Command command = mainParser.getCommandFromUserInput();
                command.execute();
                fileManager.save();
            } catch (Exception e) {
                UI.print("error occured, sus. " + e.getMessage());
            }
        }
    }
    private void loadTasksFromFile() {
        boolean hasNoFileErrors = fileManager.openFile();
        if (!hasNoFileErrors) {
            UI.print("continuing without file");
            return;
        }
        while (fileManager.hasNextLine()) {
            String line = fileManager.readNextLine();
            Command loadCommand = mainParser.getCommandFromFileInput(line);
            loadCommand.execute();
        }
    }
    private void saveTasksToFile() {
        String expenseListToSave = expenseManager.getExpensesStringRepresentations();
        fileManager.save(expenseListToSave);
    }
}
