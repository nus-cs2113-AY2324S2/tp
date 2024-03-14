package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.ItemList;

import static org.junit.jupiter.api.Assertions.*;

class ListCommandTest {

    @Test
    void execute_listCommandWithTwoItemsInItemList_correctPrintFormatForBothItems() {
        ItemList itemList = new ItemList();

        itemList.addItem("testItem", "1");
        itemList.addItem("testItem", "2");

        ListCommand listCommand = new ListCommand(itemList);

        String actualOutput = listCommand.execute();

        String expectedOutput = "testItem: 1" + System.lineSeparator() +
                "testItem: 2" + System.lineSeparator();

        assertEquals(expectedOutput, actualOutput);
    }
}