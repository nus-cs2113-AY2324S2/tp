package seedu.duke;

import seedu.duke.exceptions.InvalidGameException;
import seedu.duke.exceptions.InvalidTTMoveException;

import static seedu.duke.TicTacToe.runTicTacToe;

public class Duke {
    private static final Ui ui = new Ui();
    private static final HangMan hangman = new HangMan();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ui.greetUser();
        //ui.byeUser();

        String input = "";
        boolean inGame = false;
        input = Parser.readLine();
        while (true) {


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
                        hangman.runHangMan();
                    }
                } catch (InvalidGameException | NullPointerException e) {
                    ui.println("invalid game");
                }
            }
            input = Parser.readLine();
        }
    }
}
