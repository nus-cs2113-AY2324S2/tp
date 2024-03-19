package seedu.duke;

import seedu.duke.exceptions.InvalidGameException;
import seedu.duke.exceptions.InvalidTTMoveException;

import static seedu.duke.TicTacToe.runTicTacToe;
import static seedu.duke.HangMan.runHangMan;

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
        input = Parser.readLine();
        while (input != null) {


            if (Parser.ifQuit(input)) {
                break;
            } else if (Parser.ifHelp(input)) {
                ui.printHelp();
                continue;
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
                    } else if (input.equals("hangman")) {
                        runHangMan();
                    }
                } catch (InvalidGameException | NullPointerException e) {
                    ui.println("invalid game");
                }
            }
            input = Parser.readLine();
        }
    }
}
