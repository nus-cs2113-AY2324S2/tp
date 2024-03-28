package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.item.Item;
import seedu.binbash.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {

    @Test
    void execute_deleteCommandOnListWithTestItem_success() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("testItem", "A test item", 1,
                LocalDate.now(), 10.00, 5.00);

        DeleteCommand deleteCommand = new DeleteCommand("testItem");
        deleteCommand.execute(itemList);

        assertEquals(0, itemList.getItemCount());
    }
}
