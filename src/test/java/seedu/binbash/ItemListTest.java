package seedu.binbash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemListTest {

    @Test
    void deleteItem_oneItemInItemList_noItemInItemList() {
        ItemList itemList = new ItemList();
        itemList.addItem("testItem", "A test item");

        itemList.deleteItem(1);

        assertEquals(0, itemList.getItemCount());
    }
}
