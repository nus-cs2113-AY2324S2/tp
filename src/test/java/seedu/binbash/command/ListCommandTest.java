package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.item.Item;
import seedu.binbash.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {

    @Test
    void execute_listCommandWithTwoItemsInItemList_correctPrintFormatForBothItems() {
        ItemList itemList = new ItemList(new ArrayList<Item>());

        itemList.addItem("testItem1", "Test item 1", 2,
                Optional.of(LocalDate.of(1999, 1, 1)), 4.00, 5.00);
        itemList.addItem("testItem2", "Test item 2", 6,
                Optional.of(LocalDate.of(1999, 1, 1)), 8.00, 9.00);

        ListCommand listCommand = new ListCommand(itemList);

        listCommand.execute();
        String actualOutput = listCommand.getExecutionUiOutput();

        String expectedOutput = "1. [P][R] testItem1" + System.lineSeparator() +
                "\tdescription: Test item 1" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\texpiry date: 01-01-1999" + System.lineSeparator() +
                "\tsale price: $4.00" + System.lineSeparator() +
                "\tcost price: $5.00" + System.lineSeparator() +
                System.lineSeparator() +
                "2. [P][R] testItem2" + System.lineSeparator() +
                "\tdescription: Test item 2" + System.lineSeparator() +
                "\tquantity: 6" + System.lineSeparator() +
                "\texpiry date: 01-01-1999" + System.lineSeparator() +
                "\tsale price: $8.00" + System.lineSeparator() +
                "\tcost price: $9.00" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expectedOutput, actualOutput);
    }
}
