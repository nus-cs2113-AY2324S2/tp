package parser;

import customexceptions.UnknownPromptException;
import financialtransactions.TransactionManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    TransactionManager manager = new TransactionManager();

    @Test
    public void addInflow_success() throws UnknownPromptException {
        Parser test1 = new Parser();
        assertEquals("Ok. Added inflow", test1.parseCommand("add-inflow Salary 5000 14/03/2024 1700", manager));
        assertEquals("Ok. Added inflow", test1.parseCommand("add-inflow Investment 600 03/03/2024 1900", manager));
    }

    public void addOutflow_success() throws UnknownPromptException {
        Parser test1 = new Parser();
        assertEquals("Ok. Added outflow", test1.parseCommand("add-outflow Rent 1000 18/02/2024 1100", manager));
        assertEquals("Ok. Added outflow", test1.parseCommand("add-outflow Shopping 150 30/01/2024 1430", manager));
    }

}
