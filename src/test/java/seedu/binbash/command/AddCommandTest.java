package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.item.Item;
import seedu.binbash.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    void execute_item_oneItemInItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        AddCommand addCommand = new AddCommand("testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00);

        addCommand.execute(itemList);
        assertEquals(1, itemList.getItemCount());
    }
}
