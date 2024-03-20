package tictactoe;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    private char[][] board = new char[3][3];
    private char playerMark;
    private boolean isGameOver = false;

    public TicTacToe(char playerMark) {
        this.playerMark = playerMark;
        initializeBoard();
    }

    private boolean checkForWin() {
        return checkRowForWin() || checkColumnForWin() || checkDiagonalForWin();
    }

    private boolean checkCellForWin(char c1, char c2, char c3) {
        if (c1 == playerMark && c2 == playerMark && c3 == playerMark) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRowForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkCellForWin(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkCellForWin(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalForWin() {
        if (checkCellForWin(board[0][0], board[1][1], board[2][2]) ||
            checkCellForWin(board[1][2], board[1][1], board[2][0])) {
            return true;
        }
        return false;
    }

    private void checkGameOver() {
        if (checkForWin()) {
            System.out.println();
            printBoard();
            System.out.println();
            System.out.println("Siuuuuu, player " + playerMark + " wins!");
            isGameOver = true;
        } else if (isBoardFull()) {
            printBoard();
            System.out.println("Wow, it's a draw!");
            isGameOver = true;
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean placeMark(int row, int column) {
        if (row >= 0 && row < 3 && column >= 0 && column < 3 &&
            board[row][column] == '-') {
            board[row][column] = playerMark;
            printBoard();
            return true;
        }
        return false;
    }

    private void placeAIMark() {
        playerMark = (playerMark == 'X') ? 'O' : 'X';
        Random rand = new Random();
        int row;
        int column;

        do {
            row = rand.nextInt(3);
            column = rand.nextInt(3);
        } while (board[row][column] != '-');

        board[row][column] = playerMark;
        printBoard();
        checkGameOver();
    }

    public void gameStart() {
        Scanner scanner = new Scanner(System.in);
        printBoard();
        while (!isGameOver) {
            System.out.println("Player " + playerMark + ", " +
                "enter your move (row [1-3] column [1-3]):");
            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;

            if (placeMark(row, column)) {
                checkGameOver();
                if (!isGameOver) {
                    System.out.println("AI's turn!");
                    placeAIMark();
                    playerMark = (playerMark == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move, please try again!");
            }
        }
    }
}
