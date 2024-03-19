package seedu.binbash;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemListTest {

    @Test
    void deleteItem_indexOfItemInItemList_itemRemovedFromItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("testItem", "A test item", 2,
                "3", 4.00, 5.00);

        itemList.deleteItem(1);

        assertEquals(0, itemList.getItemCount());
    }

    @Test
    void deleteItem_nameOfItemInItemList_itemRemovedFromItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("testItem", "A test item", 2,
                "3", 4.00, 5.00);

        itemList.deleteItem("testItem");

        assertEquals(0, itemList.getItemCount());
    }

    @Test
    void deleteItem_nameOfItemNotInItemList_itemNotRemovedFromItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("testItem", "A test item", 2,
                "3", 4.00, 5.00);

        itemList.deleteItem("notTestItem");

        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void addItem_noItemInItemList_oneItemInItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());

        itemList.addItem("testItem", "A test item", 2,
                "3", 4.00, 5.00);
        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void addItem_itemInputs_correctItemParameters() {
        ItemList itemList = new ItemList(new ArrayList<Item>());

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
        ItemList itemList = new ItemList(new ArrayList<Item>());

        itemList.addItem("testItem1", "Test item 1", 2,
                "3", 4.00, 5.00);
        itemList.addItem("testItem2", "Test item 2", 6,
                "7", 8.00, 9.00);

        String actualOutput = itemList.printList(itemList.getItemList());

        String expectedOutput = "1. testItem1" + System.lineSeparator() +
                "\tdescription: Test item 1" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\texpiry date: 3" + System.lineSeparator() +
                "\tsale price: $4.00" + System.lineSeparator() +
                "\tcost price: $5.00" + System.lineSeparator() +
                System.lineSeparator() +
                "2. testItem2" + System.lineSeparator() +
                "\tdescription: Test item 2" + System.lineSeparator() +
                "\tquantity: 6" + System.lineSeparator() +
                "\texpiry date: 7" + System.lineSeparator() +
                "\tsale price: $8.00" + System.lineSeparator() +
                "\tcost price: $9.00" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expectedOutput, actualOutput);
    }
}
