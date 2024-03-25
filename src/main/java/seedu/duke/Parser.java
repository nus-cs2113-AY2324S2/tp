package seedu.duke;

import seedu.duke.exceptions.InvalidGameException;
import seedu.duke.exceptions.InvalidTTMoveException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * read user input and call other functions accordingly
 */
public class Parser {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static boolean ifQuit(String input) {
        if (input != null && input.equals("quit")) {
            return true;
        }
        return false;
    }

    public static boolean ifHelp(String input) {
        if (input != null && input.equals("help")) {
            return true;
        }
        return false;
    }

    /**
     * Reads input from user specifiying what game they wish to play.
     * Calls internal function to start the indicated game, if valid.
     * rn it returns nothing but maybe it can return a string of the game name ?
     * whatever makes it flow easier
     */
    public static void readGame(String input) throws InvalidGameException {
        if (!input.equals("TTT") && !input.equals("hangman") && !ifHelp(input) && !ifQuit(input)) {
            throw new InvalidGameException();
        }
    }

    /**
     * Reads input from user specifiying what box they wish to mark in Tic Tac Toe
     * Calls internal function to perform the indicated move, if valid
     * rn it returns nothing but maybe it can return the int repping the box num ?
     * whatever makes it flow easier
     */
    public static void readTTMove(String input) throws InvalidTTMoveException {
        if (input == null) {
            return;
        }

        int markBox;
        try {
            markBox = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidTTMoveException();
        }

        if (markBox < 1 || markBox > 9) {
            // call function to Ui to say "invalid move"
            throw new InvalidTTMoveException();
        }
    }

    /**
     * Reads input from user by line
     * Catches IOException if thrown
     *
     * @returns String representation of user input
     */
    public static String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("Error reading user input.");
        }
        return null;
    }
}

