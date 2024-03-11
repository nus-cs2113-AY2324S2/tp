package seedu.binbash;

import java.util.List;
import java.util.ArrayList;

public class ItemList {
    private final List<Item> itemList;

    public ItemList() {
        itemList = new ArrayList<>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Test method
     */
    public void addItem(Item item) {
        itemList.add(item);
    }

    @Override
    public String toString() {
        return itemList.toString();
    }
}
