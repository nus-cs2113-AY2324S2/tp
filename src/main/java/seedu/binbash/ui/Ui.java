package seedu.binbash.ui;

import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to BinBash!";
    private static final String GOODBYE_MESSAGE = "Bye!";
    private static final String LINE_DIVIDER = "-------------------------------------------------------------";

    private Scanner in;
    private boolean isUserActive;

    public Ui() {
        in = new Scanner(System.in);
        isUserActive = true;
    }

    public boolean isUserActive() {
        return isUserActive;
    }
}
