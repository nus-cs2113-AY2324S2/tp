package seedu.brokeculator;

public class Brokeculator {
    private static final String GREETING = "Hello! I'm Brokeculator!"
            + System.lineSeparator()
            + "What can I do for you?";
    public static void main(String[] args){
        Logic driverLogic = new Logic();
        UI.print(GREETING);
        driverLogic.run();
    }
}
