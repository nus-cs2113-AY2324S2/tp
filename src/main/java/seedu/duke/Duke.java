package seedu.duke;

import seedu.duke.exceptions.InvalidGameException;

public class Duke {
    private static Ui ui = new Ui();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ui.greetUser();
        //ui.byeUser();

        String input = "";
        boolean inGame = false;

        while (input != null) {
            input = Parser.readLine();

            if (Parser.ifQuit(input)) {
                break;
            } else if (Parser.ifHelp(input)) {
                ui.printHelp();
            }

            if (!inGame) {
                //ui.askGame();
                try {
                    Parser.readGame(input);
                    inGame = true;
                    // call the game they want
                } catch (InvalidGameException e) {
                    System.out.println("invalid game");
                }
            }
        }
    }
}
