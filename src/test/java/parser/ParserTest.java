package parser;

import org.junit.jupiter.api.Test;

import command.AddInflowCommand;
import command.AddOutflowCommand;
import userinterface.UI;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void sampleTest() throws Exception{
        UI ui = new UI();
        Parser parser = new Parser(ui);
        String assert1 = "add-inflow n/Salary a/400.00 d/23/05/2022 t/1900 c/income";
        String assert2 = "add-outflow n/Rent a/1500.00 d/23/06/2023 t/1800 c/rent\n";
        assertTrue(parser.parseCommand(assert1) instanceof AddInflowCommand);
        assertTrue(parser.parseCommand(assert2) instanceof AddOutflowCommand);

    }
}
