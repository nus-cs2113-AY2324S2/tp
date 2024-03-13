import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * read user input and call other functions accordingly
 */
public class Parser {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String input = "";


    /**
     * Reads input from user specifiying what game they wish to play.
     * Calls internal function to start the indicated game, if valid.
     * rn it returns nothing but maybe it can return a string of the game name ?
     * whatever makes it flow easier
     */
    public static void readGame() {
        input = readLine();
        if (input == null) {
            // call function to Ui to say something is wrong with reader
            // maybe restart program ?
        }

        if (input.equals("Tic Tac Toe")) {
            // call tic tac toe
        } else if (input.equals("Hangman")) {
            // call hangman
        } else if (input.equals("quit")) {
            // call function to tell someone else they want to quit
        } else {
            // call "unknown game entered for Ui to print something and reprompt user
        }
    }

    /**
     * Reads input from user specifiying what box they wish to mark in Tic Tac Toe
     * Calls internal function to perform the indicated move, if valid
     * rn it returns nothing but maybe it can return the int repping the box num ?
     * whatever makes it flow easier
     */
    public static void readTTMove() {
        input = readLine();
        if (input == null) {
            // call function to Ui to say something is wrong with reader
            // maybe restart program ?
        }

        int markBox = 0;
        try {
            markBox = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            if (input.equals("quit")) {
                // tell some other class they wanna quit
                return;
            }
            // call function to Ui to say "not a number, invalid move"
        }

        if (markBox < 1 || markBox > 9) {
            // call function to Ui to say "invalid move"
        } else {
            // call function to TT game to mark that box as user's
        }
    }

    /**
     * Reads input from user by line
     * Catches IOException if thrown
     *
     * @returns String representation of user input
     */
    private static String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("Error reading user input.");
        }
        return null;
    }
}
