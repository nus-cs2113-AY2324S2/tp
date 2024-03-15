package command;

import model.Order;
import model.OrdersList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
    @Test
    public void testExitCommand() {
        ExitCommand exitCommand = new ExitCommand();
        OrdersList ordersList = new OrdersList();
        Order order = new Order();
        exitCommand.execute(ordersList, order);
        assertTrue(exitCommand.isExit());
    }
}
