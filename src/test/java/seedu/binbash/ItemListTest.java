package seedu.binbash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemListTest {

    @Test
    void deleteItem_oneItemInItemList_noItemInItemList() {
        ItemList itemList = new ItemList();
        itemList.addItem("testItem", "A test item", 2,
                "3", 4.00, 5.00);

        itemList.deleteItem(1);

        assertEquals(0, itemList.getItemCount());
    }

    @Test
    void addItem_noItemInItemList_oneItemInItemList() {
        ItemList itemList = new ItemList();

        itemList.addItem("testItem", "A test item", 2,
                "3", 4.00, 5.00);
        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void addItem_itemInputs_correctItemParameters() {
        ItemList itemList = new ItemList();

        itemList.addItem("testItem", "A test item", 2,
                "3", 4.00, 5.00);
        Item item = itemList.getItemList().get(0);

        assertEquals(item.getItemName(), "testItem");
        assertEquals(item.getItemDescription(), "A test item");
        assertEquals(item.getItemQuantity(), 2);
        assertEquals(item.getItemExpirationDate(), "3");
        assertEquals(item.getItemSalePrice(), 4.00);
        assertEquals(item.getItemCostPrice(), 5.00);
    }

    @Test
    void printList_twoItemsInItemList_correctPrintFormatForBothItems() {
        ItemList itemList = new ItemList();

        itemList.addItem("testItem1", "Test item 1", 2,
                "3", 4.00, 5.00);
        itemList.addItem("testItem2", "Test item 2", 6,
                "7", 8.00, 9.00);

        String actualOutput = itemList.printList(itemList.getItemList());

        String expectedOutput = "testItem: 1" + System.lineSeparator() +
                "testItem: 2" + System.lineSeparator();

        assertEquals(expectedOutput, actualOutput);
    }
}
