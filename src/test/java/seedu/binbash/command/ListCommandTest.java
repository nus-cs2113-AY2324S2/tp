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

        String expectedOutput = "testItem: 1" + System.lineSeparator() +
                "testItem: 2" + System.lineSeparator();

        assertEquals(expectedOutput, actualOutput);
    }
}
