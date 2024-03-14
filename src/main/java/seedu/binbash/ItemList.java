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

    public int getItemCount() {
        return itemList.size();
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public String deleteItem(int index) {
        Item tempItem = itemList.remove(index - 1);

        String output = "Got it! I've removed the following item:"
                + String.format("\t%s", tempItem);
        return output;
    }

    /**
     * DO LET ME KNOW IF THE METHOD NAME IS WEIRD. IM RETURNING A STRING REPRESENTATION INSTEAD
     * OF CALLING SOUT TO STAY CONSISTENT WITH THE OTHER COMMANDS BEHAVIOUR. SO IT DOESN'T ACTUALLY
     * PRINT THE LIST. IF THERES A BETTER NAME LMK THANKS
     *
     * Returns a string representation of all the items in the list. Each item's string
     * representation is obtained by calling its `toString` method.
     *
     * @return A concatenated string of all item representations in the list, each on a new line.
     */
    public String printList() {
        String output = "";

        for (Item item: itemList) {
            output += item.toString() + System.lineSeparator();
        }

        return output;
    }

    @Override
    public String toString() {
        return itemList.toString();
    }
}
