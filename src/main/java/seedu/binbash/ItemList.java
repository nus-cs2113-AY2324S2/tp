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

    public String deleteItem(int index) {
        Item tempItem = itemList.get(index - 1);
        itemList.remove(index - 1);

        String output = "Got it! I've removed the following item:"
                + String.format("\t%s", tempItem);
        return output;
    }

    @Override
    public String toString() {
        return itemList.toString();
    }
}
