package minigame;

import java.util.Scanner;
import java.util.Random;
import ui.ResponseManager;

public class TicTacToe implements MiniGame {
    private char[][] board = new char[3][3];
    private char playerMark;
    private  char aiMark;
    private  char currentMark;
    private boolean isGameOver = false;

    public TicTacToe(char playerMark) {
        this.playerMark = playerMark;
        this.aiMark = (playerMark == 'X') ? 'O' : 'X';
        this.currentMark = playerMark;
        initializeBoard();
    }

    private boolean checkForWin() {
        return checkRowForWin() || checkColumnForWin() || checkDiagonalForWin();
    }

    private boolean checkCellForWin(char c1, char c2, char c3) {
        if (c1 == currentMark && c2 == currentMark && c3 == currentMark) {
            return true;
        }
        return false;
    }

    public int getStatus() {
        if (checkForWin()) {
            if (currentMark == playerMark) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
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
        if (checkForWin() || isBoardFull()) {
            outputResult();
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
        String boardInfor = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardInfor += board[i][j] + " ";
            }
            boardInfor += "\n";
        }
        ResponseManager.printBoard(boardInfor);
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
        currentMark = aiMark;
        Random rand = new Random();
        int row;
        int column;

        do {
            row = rand.nextInt(3);
            column = rand.nextInt(3);
        } while (board[row][column] != '-');

        board[row][column] = aiMark;
        printBoard();
        checkGameOver();
    }

    public void outputResult() {
        if (checkForWin()) {
            if (currentMark == playerMark) {
                printBoard();
                ResponseManager.indentPrint("Siuuuuu, player " + playerMark + " wins!");
            } else {
                ResponseManager.indentPrint("Noooooo, player " + playerMark + " lose the game");
            }
        } else {
            printBoard();
            ResponseManager.indentPrint("Wow, it's a draw!");
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        printBoard();
        while (!isGameOver) {
            ResponseManager.indentPrint("Player " + playerMark + ", " +
                "enter your move (row [1-3] column [1-3]):");

            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;

            if (placeMark(row, column)) {
                checkGameOver();
                if (!isGameOver) {
                    ResponseManager.indentPrint("AI's turn!");
                    placeAIMark();
                    currentMark = playerMark;
                }
            } else {
                ResponseManager.indentPrint("Invalid move, please try again!");
            }
        }
    }
}
