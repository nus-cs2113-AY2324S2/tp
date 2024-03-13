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
<<<<<<< HEAD
    public void startEchoPunctuation(){
        assertEquals("!!??::,,//", Echo.echoInput("!!??::,,//"));
    }


=======
    public void startEchoSymbols() {
        assertEquals("#$%^&", Echo.echoInput("#$%^&"));

    }
>>>>>>> a3aa00a922b6614164e1b8d74da2c241be7e5503
}
