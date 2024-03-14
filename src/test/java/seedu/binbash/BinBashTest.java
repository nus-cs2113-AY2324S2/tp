package seedu.binbash;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class BinBashTest {
    private static final String NEWLINE = System.lineSeparator();
    private static final String TEST_INPUT = "bye";
    private static final String EXPECTED_OUTPUT = "-------------------------------------------------------------" +
            NEWLINE +
            "  ____  _       ____            _" + NEWLINE +
            " | __ )(_)_ __ | __ )  __ _ ___| |__" + NEWLINE +
            " |  _ \\| | '_ \\|  _ \\ / _` / __| '_ \\" + NEWLINE +
            " | |_) | | | | | |_) | (_| \\__ \\ | | |" + NEWLINE +
            " |____/|_|_| |_|____/ \\__,_|___/_| |_|" + NEWLINE + NEWLINE +
            "Welcome to BinBash!" + NEWLINE +
            "-------------------------------------------------------------" + NEWLINE +
            "-------------------------------------------------------------" + NEWLINE +
            "Bye!" + NEWLINE +
            "-------------------------------------------------------------" + NEWLINE;
    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Test
    public void main_readByeCommand_byeCommandExecuted() {
        try {
            setupInput();
            setupOutput();
            BinBash.main(new String[0]);
            assertEquals(getOutput(), EXPECTED_OUTPUT);
        } finally {
            restoreSystemInputOutput();
        }
    }

    private void setupInput() {
        testIn = new ByteArrayInputStream(TEST_INPUT.getBytes());
        System.setIn(testIn);
    }

    private void setupOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getOutput() {
        return testOut.toString();
    }

    private void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
}
