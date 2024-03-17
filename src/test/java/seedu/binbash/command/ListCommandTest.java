package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.ItemList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {

    @Test
    void execute_listCommandWithTwoItemsInItemList_correctPrintFormatForBothItems() {
        ItemList itemList = new ItemList();

        itemList.addItem("testItem1", "Test item 1", 2,
                "3", 4.00, 5.00);
        itemList.addItem("testItem2", "Test item 2", 6,
                "7", 8.00, 9.00);

        ListCommand listCommand = new ListCommand(itemList);

        String actualOutput = listCommand.execute();

        String expectedOutput = "testItem1" + System.lineSeparator() +
                "\tdescription: Test item 1" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\texpiry date: 3" + System.lineSeparator() +
                "\tsale price: $4.00" + System.lineSeparator() +
                "\tcost price: $5.00" + System.lineSeparator() +
                System.lineSeparator() +
                "testItem2" + System.lineSeparator() +
                "\tdescription: Test item 2" + System.lineSeparator() +
                "\tquantity: 6" + System.lineSeparator() +
                "\texpiry date: 7" + System.lineSeparator() +
                "\tsale price: $8.00" + System.lineSeparator() +
                "\tcost price: $9.00" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expectedOutput, actualOutput);
    }
}
