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
    public String addItem(String itemName, String itemDescription) {
        Item item = new Item(itemName, itemDescription);
        itemList.add(item);

        String output = "Noted! I have added the following item into your inventory:"
                + String.format("\t%s", item);
        return output;
    }

    public String deleteItem(int index) {
        Item tempItem = itemList.remove(index - 1);

        String output = "Got it! I've removed the following item:"
                + String.format("\t%s", tempItem);
        return output;
    }

    @Override
    public String toString() {
        return itemList.toString();
    }
}
