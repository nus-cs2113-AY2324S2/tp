package seedu.duke;

import seedu.duke.exceptions.InvalidTTMoveException;

import java.util.Scanner;

import java.util.Random;

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

    public static String checkWinner(int turnCount) {
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
            } else if (line.equals("OOO")) {
                return "O";
            }
        }
        if (turnCount == 9) {
            return "draw";
        }
        return "unending";
    }

    @Override public void runTicTacToe() throws InvalidTTMoveException {
        for (int i = 0; i < 9; i++) {
            board[i] = " ";
        }

        Random rand = new Random();

        int turnCount = 0;

        Scanner in = new Scanner(System.in);
        String line;
        while (checkWinner(turnCount).equals("unending")) {
            printBoard();
            System.out.println("----------------------------------------------------");
            System.out.println("Make your move, challenger.");
            line = in.nextLine();
            assert line != null;

            if (line.equals("quit")) {
                break;
            }

            try {
                readTTMove(line);
                board[Integer.parseInt(line) - 1] = "X";
                turnCount++;

                if (turnCount == 9) {
                    break;
                }

                int randomPlacement = rand.nextInt(9);

                while (board[randomPlacement].equals("X") ||
                        board[randomPlacement].equals("O")) {
                    randomPlacement = rand.nextInt(9);
                }

                board[randomPlacement] = "O";
                turnCount++;
            } catch (InvalidTTMoveException e) {
                System.out.println("Your move is invalid, invalid. Enter only 1-9, and do not make me ask again.");
            }
        }
        String whoWon = checkWinner(turnCount);
        switch (whoWon) {
        case "X":
            printBoard();
            System.out.println("----------------------------------------------------");
            System.out.println("You have claimed victory over the skies. Godspeed, champion.");
            break;
        case "O":
            printBoard();
            System.out.println("----------------------------------------------------");
            System.out.println("Your defeat has brought shame to the skies. Try again, if you dare.");
            break;
        case "draw":
            printBoard();
            System.out.println("----------------------------------------------------");
            System.out.println("It seems you have met your match. Try again, and this time, do try to win.");
            break;
        default:
            System.out.println("----------------------------------------------------");
            System.out.println("Cowards belong on the ground.");
            break;
        }
    }
}
