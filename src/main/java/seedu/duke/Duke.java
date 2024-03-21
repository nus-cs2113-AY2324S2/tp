package seedu.duke;

import seedu.duke.exception.ProcessInputException;

public class Duke {
    private final Ui u1;
    private static Parser userCommandReader;
    private static boolean isTerminate = true;

    public Duke() {
        this.u1 = new Ui();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Duke d1=new Duke();
        d1.runLogic();
    }

    private void runLogic() {
        u1.printGreet();
        while (isTerminate) {
            Formatter.printGoalBeforeShot(1);
            processUserInput();
        }
        u1.printBye();
    }

    private void executeCommand() {
        CommandList selectedCommand = CommandList.valueOf(userCommandReader.getCommandName());

        switch (selectedCommand) {
        case BYE:
            u1.printBye();
            isTerminate = false;
            break;
        case SHOOT:
            CommandList.executeShoot(userCommandReader);
            break;
        default:
            Formatter.printErrorUnknown();
        }
    }

    private void processUserInput(){
        try {
            u1.userInputCatcher();
            u1.processInput();
            executeCommand();
        } catch (ProcessInputException e) {
            Formatter.printErrorExecutionFail();
        }
    }
}