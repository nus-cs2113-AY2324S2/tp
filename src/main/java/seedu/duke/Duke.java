package seedu.duke;

import seedu.duke.exceptions.InvalidGameException;
import seedu.duke.exceptions.InvalidTTMoveException;

import static seedu.duke.TicTacToe.runTicTacToe;

public class Duke {
    private static final Ui ui = new Ui();

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
                    if (input.equals("TTT")) {
                        try {
                            runTicTacToe();
                        } catch (InvalidTTMoveException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (InvalidGameException e) {
                    System.out.print("invalid game");
                }
            }
        }
    }
}
