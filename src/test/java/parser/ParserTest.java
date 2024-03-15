package parser;

import financialtransactions.TransactionManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    TransactionManager manager = new TransactionManager();

    @Test
    public void addInflow_success() {
        assertEquals("Ok. Added inflow", new Parser().parseCommand("add-inflow Salary 5000 14/03/2024 1700", manager));
        assertEquals("Ok. Added inflow", new Parser().parseCommand("add-inflow Investment 600 03/03/2024 1900", manager));
    }

    public void addOutflow_success() {
        assertEquals("Ok. Added outflow", new Parser().parseCommand("add-outflow Rent 1000 18/02/2024 1100", manager));
        assertEquals("Ok. Added outflow", new Parser().parseCommand("add-outflow Shopping 150 30/01/2024 1430", manager));
    }

}
