package parser;

import financialtransactions.TransactionManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    TransactionManager manager = new TransactionManager();

    @Test
    public void addInflow_success() {
        Parser test1 = new Parser();
        assertEquals("Ok. Added inflow",
                     test1.parseCommand("add-inflow n/Salary a/5000 d/14/03/2024 t/1700 c/income", manager));
        assertEquals("Ok. Added inflow",
                     test1.parseCommand("add-inflow n/Stocks a/600 d/03/03/2024 t/1900 c/investment", manager));
    }

    public void addOutflow_success() {
        Parser test1 = new Parser();
        assertEquals("Ok. Added outflow",
                     test1.parseCommand("add-outflow n/Rent a/1000 d/18/02/2024 t/1100 c/rent", manager));
        assertEquals("Ok. Added outflow",
                     test1.parseCommand("add-outflow n/Shopping a/150 d/30/01/2024 t/1430 c/shopping", manager));
    }

}
