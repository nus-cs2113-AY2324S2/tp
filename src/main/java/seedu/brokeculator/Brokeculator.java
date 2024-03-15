package seedu.brokeculator;

public class Brokeculator {
    private static final String GREETING = "Hello! I'm Brokeculator!"
            + System.lineSeparator()
            + "What can I do for you?";
    public static void main(String[] args){
        GeneralInputParser mainParser = new GeneralInputParser();
        FileManager fileManager = new FileManager();
        ExpenseManager expenseManager = new ExpenseManager();
        Logic driverLogic = new Logic(mainParser, fileManager, expenseManager);
        UI.print(GREETING);
        driverLogic.run();
    }
}
