package seedu.binbash.ui;

import java.util.Scanner;

public class Ui {
    private static final String NEWLINE = System.lineSeparator();
    private static final String LOGO = "  ____  _       ____            _" + NEWLINE +
            " | __ )(_)_ __ | __ )  __ _ ___| |__" + NEWLINE +
            " |  _ \\| | '_ \\|  _ \\ / _` / __| '_ \\" + NEWLINE +
            " | |_) | | | | | |_) | (_| \\__ \\ | | |" + NEWLINE +
            " |____/|_|_| |_|____/ \\__,_|___/_| |_|" + NEWLINE + NEWLINE;
    private static final String WELCOME_MESSAGE = "Welcome to BinBash!";
    private static final String GOODBYE_MESSAGE = "Bye!";
    private static final String LINE_DIVIDER = "-------------------------------------------------------------";

    private final Scanner in;
    private boolean isUserActive;

    public Ui() {
        in = new Scanner(System.in);
        isUserActive = true;
    }

    public boolean isUserActive() {
        return isUserActive;
    }

    public void setUserAsInactive() {
        isUserActive = false;
    }

    public String readUserCommand() {
        return in.nextLine();
    }

    public void greet() {
        talk(LOGO + WELCOME_MESSAGE);
    }

    public void farewell() {
        talk(GOODBYE_MESSAGE);
    }

    public void talk(String line) {
        System.out.println(LINE_DIVIDER + NEWLINE + line + NEWLINE + LINE_DIVIDER);
    }
}
