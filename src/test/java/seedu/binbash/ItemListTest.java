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

    @Test
    void addItem_noItemInItemList_oneItemInItemList() {
        ItemList itemList = new ItemList();

        itemList.addItem("testItem", "A test item");
        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void addItem_itemNameAndDescription_correctItemNameAndDescription() {
        ItemList itemList = new ItemList();

        itemList.addItem("testItem", "A test item");
        Item item = itemList.getItemList().get(0);

        assertEquals(item.getItemName(), "testItem");
        assertEquals(item.getItemDescription(), "A test item");
    }

    @Test
    void printList_twoItemsInItemList_correctPrintFormatForBothItems() {
        ItemList itemList = new ItemList();

        itemList.addItem("testItem", "1");
        itemList.addItem("testItem", "2");

        String actualOutput = itemList.printList(itemList.getItemList());

        String expectedOutput = "testItem: 1" + System.lineSeparator() +
                "testItem: 2" + System.lineSeparator();

        assertEquals(expectedOutput, actualOutput);
    }
}
