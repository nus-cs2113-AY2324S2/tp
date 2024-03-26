package supertracker.command;

import supertracker.ui.Ui;
import supertracker.item.Inventory;
import supertracker.item.Item;

import java.util.Collections;
import java.util.List;

public class ListCommand implements Command {
    private static final String QUANTITY_FLAG = "q";
    private static final String PRICE_FLAG = "p";
    private boolean hasQuantity;
    private boolean hasPrice;
    private String firstParam;
    private String sortBy;
    private boolean reverse;

    public ListCommand(boolean hasQuantity, boolean hasPrice, String firstParam, String sortBy, boolean reverse) {
        this.hasQuantity = hasQuantity;
        this.hasPrice = hasPrice;
        this.firstParam = firstParam;
        this.sortBy = sortBy;
        this.reverse = reverse;
    }

    @Override
    public void execute() {
        assert isValid(firstParam);
        assert isValid(sortBy);

        int index = 1;
        List<Item> items = Inventory.getItems();
        Ui.listIntro(items.size());

        switch (sortBy) {
        case QUANTITY_FLAG:
            items.sort(Item.sortByQuantity());
            break;
        case PRICE_FLAG:
            items.sort(Item.sortByPrice());
            break;
        default:
            items.sort(Item.sortByName());
            break;
        }

        if (reverse) {
            Collections.reverse(items);
        }

        for (Item item : items) {
            Ui.listItem(item, index, hasQuantity, hasPrice, firstParam);
            index++;
        }
    }

    @Override
    public boolean isQuit() {
        return false;
    }

    /**
     * Checks if the provided string is valid.
     *
     * @param s The string to be validated.
     * @return {@code true} if the string is equal to "q" or "p",
     *         or if the string is empty; {@code false} otherwise.
     */
    private boolean isValid(String s) {
        return s.equals(QUANTITY_FLAG) || s.equals(PRICE_FLAG) || s.isEmpty();
    }
}
