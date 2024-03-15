package seedu.duke;

import seedu.duke.Ui.Ui;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private final Ui u1;

    public Duke() {
        this.u1 = new Ui();
    }

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
