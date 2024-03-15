package parser;

import java.util.Arrays;

import command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setup() {
        this.parser = new Parser();
    }

    @Test
    public void parse_emptyInput_returnsIncorrect() {
        final String[] emptyInputs = { "", "  ", "\n  \n" };
        parseAndAssertEmpty(emptyInputs);
    }

    public void parseAndAssertEmpty(String[] inputs) {
        for (String input : inputs) {
            final Command result = parser.parserCommand(input);
            assertNull(result);
        }
    }
}
