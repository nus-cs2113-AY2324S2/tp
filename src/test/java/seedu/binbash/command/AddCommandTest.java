package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.item.Item;
import seedu.binbash.ItemList;
import seedu.binbash.storage.Storage;
import seedu.binbash.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    private static final Ui TEST_UI = new Ui();
    private static final Storage TEST_STORAGE = new Storage();

    @Test
    void execute_item_oneItemInItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        AddCommand addCommand = new AddCommand("testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00);

        addCommand.execute(TEST_UI, itemList, TEST_STORAGE);
        assertEquals(1, itemList.getItemCount());
    }
}
