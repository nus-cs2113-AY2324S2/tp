package seedu.duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EchoTest {
    @Test
    public void startEcho() {
        assertEquals("test", Echo.echoInput("test"));

    }

    @Test
    public void startEchoNumbers() {
        assertEquals("123456", Echo.echoInput("123456"));

    }

    @Test

    public void startEchoPunctuation(){
        assertEquals("!!??::,,//", Echo.echoInput("!!??::,,//"));
    }


    @Test
    public void startEchoSymbols() {
        assertEquals("#$%^&", Echo.echoInput("#$%^&"));

    }


    @Test
    public void startEchoName() {
        assertEquals("maxbranch", Echo.echoInput("maxbranch"));

    }

}
