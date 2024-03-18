package seedu.binbash;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ItemList {
    private final List<Item> itemList;

    public ItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
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

    public String addItem(String itemName, String itemDescription, int itemQuantity, String itemExpirationDate,
                          double itemSalePrice, double itemCostPrice) {
        Item item = new Item(itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice, itemCostPrice);

        itemList.add(item);

        String output = "Noted! I have added the following item into your inventory:\n"
                + "\n" + item;
        return output;
    }

    public String deleteItem(int index) {
        Item tempItem = itemList.remove(index - 1);

        String output = "Got it! I've removed the following item:"
                + String.format("\t%s", tempItem);
        return output;
    }

    public String searchItem(String keyword) {
        ArrayList<Item> filteredList = (ArrayList<Item>) itemList.stream()
                .filter(item -> item.getItemName().contains(keyword))
                .collect(Collectors.toList());

        String output = "";

        if (filteredList.isEmpty()) {
            output += String.format("There are no tasks with the keyword '%s'!", keyword);
        } else {
            output = String.format("Here's a list of items that contain the keyword '%s': ", keyword)
                    + System.lineSeparator()
                    + printList(filteredList);
        }

        return output;
    }

    /**
     * Returns a string representation of all the items in the list. Each item's string
     * representation is obtained by calling its `toString` method.
     *
     * @return A concatenated string of all item representations in the list, each on a new line.
     */
    public String printList(List<Item> itemList) {
        String output = "";

        for (Item item: itemList) {
            output += item.toString() + System.lineSeparator() + System.lineSeparator();
        }

        return output;
    }

    @Override
    public String toString() {
        return itemList.toString();
    }
}
