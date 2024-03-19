package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.Item;
import seedu.binbash.ItemList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {

    @Test
    void execute() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("testItem", "A test item", 1,
                "next week", 10.00, 5.00);

        DeleteCommand deleteCommand = new DeleteCommand(itemList, "testItem");

        assertEquals(1, itemList.getItemCount());
    }
}
