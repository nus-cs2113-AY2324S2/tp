package minigame;

import java.util.Scanner;
import java.util.Random;
import java.util.logging.Logger;

import exception.InvalidMoveException;
import ui.ResponseManager;

public class TicTacToe implements MiniGame {
    private static final Logger logger = Logger.getLogger("TacLog");
    private char[][] board = new char[3][3];
    private char playerMark;
    private  char aiMark;
    private  char currentMark;
    private boolean isGameOver = false;
    private boolean isDraw = false;

    public TicTacToe(char playerMark) {
        this.playerMark = playerMark;
        this.aiMark = (playerMark == 'X') ? 'O' : 'X';
        this.currentMark = playerMark;
        initializeBoard();
        logger.info("Game initialized with player mark: " + playerMark);
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
            checkCellForWin(board[0][2], board[1][1], board[2][0])) {
            return true;
        }
        return false;
    }

    private void checkGameOver() {
        if (checkForWin()) {
            isGameOver = true;
            outputResult();
        }
        if (isBoardFull()) {
            isDraw = true;
            isGameOver = true;
            outputResult();
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
                assert board[i][j] == '-': "Board should be initialized with '-'";
            }
        }
        logger.info("Board initialized");
    }

    private void printBoard() {
        String boardInfor = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardInfor += board[i][j] + " ";
            }
            if (i <= 1) {
                boardInfor += "\n";
            }
        }
        logger.info("Board state:\n" + boardInfor);
        ResponseManager.printBoard(boardInfor);
    }

    private void placeMark(int row, int column)  throws InvalidMoveException {
        if (row >= 0 && row < 3 && column >= 0 && column < 3 &&
            board[row][column] == '-') {
            board[row][column] = playerMark;
            logger.info("Mark placed by player at [" + row + "," + column + "]");
            printBoard();
            checkGameOver();
        } else {
            logger.warning("Invalid move attempted at [" + row + "," + column + "]");
            throw new InvalidMoveException("Move at (" + (row + 1) + ", " + (column + 1) + ") is invalid.\n");
        }
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
        if (isDraw) {
            ResponseManager.indentPrint("Wow, it's a draw!\n");
        } else {
            if (currentMark == playerMark) {
                ResponseManager.indentPrint("Siuuuuu, player " + playerMark + " wins!\n");
            } else {
                ResponseManager.indentPrint("Noooooo, player " + playerMark + " lose the game\n");
            }
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        printBoard();
        while (!isGameOver) {
            ResponseManager.indentPrint("Player " + playerMark + ", " +
                "enter your move (row [1-3] column [1-3]):\n");

            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;

            try {
                placeMark(row, column);
                if (!isGameOver) {
                    ResponseManager.indentPrint("AI's turn!\n");
                    placeAIMark();
                    currentMark = playerMark;
                }
            } catch (InvalidMoveException e) {
                ResponseManager.indentPrint(e.getMessage());
            }
        }
    }
}
