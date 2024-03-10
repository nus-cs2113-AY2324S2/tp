package supertracker.item;

import java.util.HashMap;

public class Inventory {
    protected HashMap<String, Item> itemMap;

    public Inventory() {
        this.itemMap = new HashMap<>();
    }
}
