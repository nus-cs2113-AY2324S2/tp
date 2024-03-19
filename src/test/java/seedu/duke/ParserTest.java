package seedu.duke;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    // for some reason, if you run these all one by one, it passes everything
    // BUTTT if you run all of them together, they fail
    // it's a problem with the input stream reader - byte array thing i think

    @Test
    public void dummyTest(){
        assertTrue(true);
    }

    @Test
    public void checkQuit() {
        String input = "quit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean expected = true;
        assertEquals(expected, Parser.ifQuit(input));
    }

    public void checkHelp() {
        String input = "help";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean expected = true;
        assertEquals(expected, Parser.ifHelp(input));
    }

    /*
    @Test
    public void checkGameHangman() {
        String input = "Hangman";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expected = "calling hangman";
        assertEquals(expected, Parser.readGame());
    }

    @Test
    public void checkGameQuit() {
        String input = "quit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expected = "tryna quit";
        assertEquals(expected, Parser.readGame());
    }

    @Test
    public void checkGameInvalid() {
        String input = "badonk";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expected = "unknown game name ! choices: hangman, ttt, or quit";
        assertEquals(expected, Parser.readGame());
    }

    /**
     @Test
     public void checkGameNull() {
        String input = null;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expected = "null input on readGame()";
        assertEquals(expected, seedu.duke.Duke.Parser.readGame());
     }


    @Test
    public void checkTTMoveValidNum() {
        String input = "4";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expected = "doing tt move = " + input;
        assertEquals(expected, Parser.readTTMove());
    }

    @Test
    public void checkTTMoveInvalidNum() {
        String input = "13";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expected = "invalid tt move = " + input;
        assertEquals(expected, Parser.readTTMove());
    }

    @Test
    public void checkTTMoveQuit() {
        String input = "quit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expected = "tryna quit ttt";
        assertEquals(expected, Parser.readTTMove());
    }

    @Test
    public void checkTTInvalidInput() {
        String input = "hella";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expected = "invalid tt input = " + input;
        assertEquals(expected, Parser.readTTMove());
    }

     @Test
     public void checkTTMoveNull() {
         String input = null;
         InputStream in = new ByteArrayInputStream(input.getBytes());
         System.setIn(in);

         String expected = "null input on readTTMove()";
         assertEquals(expected, seedu.duke.Duke.Parser.readTTMove());
     }
     */
}


