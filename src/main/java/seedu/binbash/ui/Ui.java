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

    public void greet() {
        talk(WELCOME_MESSAGE);
    }

    public void farewell() {
        talk(GOODBYE_MESSAGE);
    }

    public void talk(String line) {
        System.out.println(LINE_DIVIDER + "\n" + line + "\n" + LINE_DIVIDER);
    }
}
