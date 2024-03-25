package seedu.binbash.ui;

import java.io.PrintWriter;

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

    private static TextIn inputReader;
    private static PrintWriter outputWriter;
    private static boolean isUserActive;

    public Ui() {
        inputReader = new TextIn();
        outputWriter = inputReader.getPrintWriter();
        isUserActive = true;
    }

    public boolean isUserActive() {
        return isUserActive;
    }

    public void setUserAsInactive() {
        isUserActive = false;
    }

    public String readUserCommand() {
        assert isUserActive();
        return inputReader.nextLine();
    }

    public void greet() {
        talk(LOGO + WELCOME_MESSAGE);
    }

    public void farewell() {
        assert !isUserActive();
        talk(GOODBYE_MESSAGE);
    }

    public void talk(String line) {
        outputWriter.println(LINE_DIVIDER + NEWLINE + line + NEWLINE + LINE_DIVIDER);
    }
}
