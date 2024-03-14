package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.ItemList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    void execute_item_oneItemInItemList() {
        ItemList itemList = new ItemList();
        AddCommand addCommand = new AddCommand(itemList, "testItem", "A test item");

        addCommand.execute();
        assertEquals(1, itemList.getItemCount());
    }
}
