package seedu.duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        return null;
    }

    public void showWelcomeMessage() {}

    public void showGoodByeMessage() {}
}
