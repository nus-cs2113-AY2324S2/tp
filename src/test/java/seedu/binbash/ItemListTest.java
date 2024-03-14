package seedu.binbash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemListTest {

    @Test
    void deleteItem_oneItemInItemList_noItemInItemList() {
        ItemList itemList = new ItemList();
        Item sampleItem = new Item("testItem", "A test item");
        itemList.addItem(sampleItem);

        itemList.deleteItem(1);

        assertEquals(0, itemList.getItemCount());
    }
}