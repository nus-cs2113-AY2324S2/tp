package parser;

import financialtransactions.TransactionManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    TransactionManager manager = new TransactionManager();

    @Test
    public void addInflow_success() {
        assertEquals("Ok. Added inflow", new Parser().parseCommand("add-inflow Salary 50000 14/03/2024 1700", manager));
    }

}
