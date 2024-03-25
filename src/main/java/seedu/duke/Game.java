package seedu.duke;

import seedu.duke.exceptions.InvalidTTMoveException;

public class Game {
    protected boolean isExit; // true to exit
    protected String gameName;

    public Game(String line) {
        gameName = line;
        isExit = false; //not used for nw
        assert !isExit; //not used for now
    }
    public void runTicTacToe() throws InvalidTTMoveException {

    }

    public void getHelp() {

    }

    public void runHangMan() {

    }
}
