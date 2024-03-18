package seedu.duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static Ui ui = new Ui();
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ui.greetUser();
        //ui.byeUser();

    }

    /**
     * read user input and call other functions accordingly
     */
    public static class Parser {
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static String input = "";


        /**
         * Reads input from user specifiying what game they wish to play.
         * Calls internal function to start the indicated game, if valid.
         * rn it returns nothing but maybe it can return a string of the game name ?
         * whatever makes it flow easier
         */
        public static String readGame() {
            input = readLine();
            if (input == null) {
                // call function to Ui to say something is wrong with reader // maybe restart program ?
                return ("null input on readGame()");
            }

            if (input.equals("Tic Tac Toe")) {
                // call tic tac toe
                return ("calling ttt");
            } else if (input.equals("Hangman")) {
                // call hangman
                return ("calling hangman");
            } else if (input.equals("quit")) {
                // call function to tell someone else they want to quit
                return ("tryna quit");
            } else {
                // call "unknown game entered for Ui to print something and reprompt user
                return ("unknown game name ! choices: hangman, ttt, or quit");
            }
        }

        /**
         * Reads input from user specifiying what box they wish to mark in Tic Tac Toe
         * Calls internal function to perform the indicated move, if valid
         * rn it returns nothing but maybe it can return the int repping the box num ?
         * whatever makes it flow easier
         */
        public static String readTTMove() {
            input = readLine();
            if (input == null) {
                // call function to Ui to say something is wrong with reader // maybe restart program ?
                return ("null input on readTTMove()");
            }

            int markBox = 0;
            try {
                markBox = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                if (input.equals("quit")) {
                    // tell some other class they wanna quit
                    return ("tryna quit ttt");
                    //return;
                }
                // call function to Ui to say "not a number, invalid move"
                return ("invalid tt input = " + input);
            }

            if (markBox < 1 || markBox > 9) {
                // call function to Ui to say "invalid move"
                return ("invalid tt move = " + markBox);
            } else {
                // call function to TT game to mark that box as user's
                return ("doing tt move = " + markBox);
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

        /* public static void main (String ars[]) {
            System.out.println("\ntest ttt");
            System.out.println(readGame());
            System.out.println("\ntest h");
            System.out.println(readGame());
            System.out.println("\ntest quit");
            System.out.println(readGame());
            System.out.println("\ntest hub");
            System.out.println(readGame());

            System.out.println("\ntest 1-9");
            System.out.println(readTTMove());
            System.out.println("\ntest 13");
            System.out.println(readTTMove());
            System.out.println("\ntest quit");
            System.out.println(readTTMove());
            System.out.println("\ntest hub");
            System.out.println(readTTMove());
        } */
    }
}
