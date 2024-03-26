package seedu.duke;

import seedu.duke.exception.ProcessInputException;
import seedu.duke.player.BeginnerSkill;
import seedu.duke.player.MediumSkill;
import seedu.duke.ui.Ui;

public class Duke {

    public Duke() {}

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Formatter.printWelcomeMsg();
//        PlayerList.L1.add(new MediumSkill("Bruno"));

        //Bruno is a player that user created
        while (Ui.getIsRunning()) {
//            Formatter.printGoalBeforeShot(Ui.roundCount);
            try {
                Ui.beginListening();
                Ui.processInput();
                Ui.executeCommand();
            } catch (ProcessInputException e) {
                Formatter.printErrorExecutionFail();
            }
        }
    }
}