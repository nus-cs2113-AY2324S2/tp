package seedu.binbash.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextInTest {
    private TextIn in = new TextIn();

    @Test
    public void nextLine_eof_returnsBye() {
        String gotLine = in.nextLine();
        assertEquals(gotLine, "bye");
    }
}
