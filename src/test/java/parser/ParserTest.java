package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    /*
     * Note how the names of the test methods does not follow the normal naming convention.
     * That is because our coding standard allows a different naming convention for test methods.
     */

    @Test
    public void parse_emptyInput_returnsIncorrect() {
    }
}
