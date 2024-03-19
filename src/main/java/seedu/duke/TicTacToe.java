package seedu.duke;

import seedu.duke.exceptions.InvalidTTMoveException;

import java.util.Scanner;

import static seedu.duke.Parser.readTTMove;

public class TicTacToe extends Game {
    protected static String[] board = new String[9];

    public TicTacToe(String line) {
        super(line);
    }

    public static void printBoard() {
        System.out.println("  " + board[0] + " | " + board[1] + " | " + board[2] + "  ");
        System.out.println("-------------");
        System.out.println("  " + board[3] + " | " + board[4] + " | " + board[5] + "  ");
        System.out.println("-------------");
        System.out.println("  " + board[6] + " | " + board[7] + " | " + board[8] + "  ");
    }

    public static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
            case 0:
                line = board[0] + board[1] + board[2];
                break;
            case 1:
                line = board[3] + board[4] + board[5];
                break;
            case 2:
                line = board[6] + board[7] + board[8];
                break;
            case 3:
                line = board[0] + board[3] + board[6];
                break;
            case 4:
                line = board[1] + board[4] + board[7];
                break;
            case 5:
                line = board[2] + board[5] + board[8];
                break;
            case 6:
                line = board[0] + board[4] + board[8];
                break;
            case 7:
                line = board[2] + board[4] + board[6];
                break;
            default:
                //this never happens
            }
            if (line.equals("XXX")) {
                return "X";
            }
        }
        return "unending";
    }

    public static void runTicTacToe() throws InvalidTTMoveException {
        for (int i = 0; i < 9; i++) {
            board[i] = " ";
        }
        Scanner in = new Scanner(System.in);
        String line = " ";
        while (checkWinner().equals("unending") || line.equals("exit")) {
            printBoard();
            line = in.nextLine();

            try {
                readTTMove(line);
                board[Integer.parseInt(line) - 1] = "X";
            } catch (InvalidTTMoveException e){
                System.out.println("Invalid move. There are only slots 1-9.");
            }
        }
        printBoard();
        System.out.println("Thank you for playing. See you next time!");
    }
}
