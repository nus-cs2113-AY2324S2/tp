package brokeculator;
import brokeculator.frontend.UI;
import brokeculator.storage.FileManager;
import brokeculator.expense.ExpenseManager;
import brokeculator.storage.parsing.GeneralFileParser;
import brokeculator.storage.parsing.GeneralInputParser;

public class Brokeculator {
    private static final String GREETING = "Hello! I'm Brokeculator!"
            + System.lineSeparator()
            + "What can I do for you?";
    public static void main(String[] args) {
        GeneralInputParser mainParser = new GeneralInputParser();
        ExpenseManager expenseManager = new ExpenseManager();
        FileManager fileManager = new FileManager();
        GeneralFileParser fileParser = new GeneralFileParser(expenseManager);
        Logic driverLogic = new Logic(mainParser, fileManager, expenseManager, fileParser);
        UI.print(GREETING);
        driverLogic.run();
    }
}
