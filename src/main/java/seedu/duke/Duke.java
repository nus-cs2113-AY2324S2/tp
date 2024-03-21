package seedu.duke;

import seedu.duke.ui.Ui;
import seedu.duke.ai.Ai;

public class Duke {
    private final Ai ai;
    private final Ui u1;


    public Duke() {
        this.ai = new Ai();
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
//        u1.printGreet();
        Formatter.printWelcomeMsg();

        while (Ui.getIsRunning()) {
            Formatter.printGoalBeforeShot(Ui.roundCount);
            try {
                Ui.beginListening();
                Ui.processInput();
                Ui.executeCommand();
            } catch (ProcessInputException e) {
                Formatter.printErrorExecutionFail();
            }

        }
//        u1.printBye();
    }


//    private boolean isTerminate(String userInput){
//        return !userInput.equalsIgnoreCase("bye");
//    }

}

