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

        boolean inGame = false;
        String input = Parser.readLine();

        while (true) {
            if (Parser.ifQuit(input)) {
                ui.byeUser();
                break;
            } else if (Parser.ifHelp(input)) {
                ui.printHelp();
            } else {
                assert input != null;
                if (input.equals("testquit")) {
                    ui.println("runtestbat success!");
                    break;
                }
            }

            if (!inGame) {
                try {
                    Parser.readGame(input);
                    inGame = true;
                    if (input.equals("TTT")) {
                        try {
                            runTicTacToe();
                            System.out.println("Now what would you like to do?");
                            inGame = false;
                        } catch (InvalidTTMoveException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (input.equals("hangman")) {
                        hangman.runHangMan();
                        System.out.println("Now what would you like to do?");
                        inGame = false;
                    }
                    if (!input.equals("help")  ) {
                        ui.printHelp();
                    }
                } catch (InvalidGameException | NullPointerException e) {
                    ui.println("Invalid Game.");
                }
            }
            input = Parser.readLine();
        }
    }
}
