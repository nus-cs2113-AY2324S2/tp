package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.item.Item;
import seedu.binbash.ItemList;
import seedu.binbash.storage.Storage;
import seedu.binbash.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {
    private static final Ui TEST_UI = new Ui();
    private static final Storage TEST_STORAGE = new Storage();

    @Test
    void execute_deleteCommandOnListWithTestItem_success() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("testItem", "A test item", 1,
                LocalDate.now(), 10.00, 5.00);

        DeleteCommand deleteCommand = new DeleteCommand("testItem");
        deleteCommand.execute(TEST_UI, itemList, TEST_STORAGE);

        assertEquals(0, itemList.getItemCount());
    }
}
