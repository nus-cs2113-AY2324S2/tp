package seedu.duke;

import seedu.duke.Ui.Ui;
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

    private void runLogic(){
        u1.printGreet();
        String userInput = u1.readUserInput();
        while (isTerminate(userInput)){
            shootFunction();
            userInput = u1.readUserInput();
        }
        u1.printBye();

    }

    private void shootFunction(){
        //        void viewgoal function;
        //        int userShoot function;
        //        int AI_goalkeeper;
        //        void viewgoal (int shoot, boolean goalCheck)
        System.out.println("Function");
    }

    private boolean isTerminate(String userInput){
        return !userInput.equalsIgnoreCase("bye");
    }
}
