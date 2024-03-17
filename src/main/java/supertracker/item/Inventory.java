package supertracker.item;

import java.util.Collection;
import java.util.HashMap;

public class Inventory {
    private static HashMap<String, Item> itemMap = new HashMap<>();

    public static boolean contains(String name) {
        return itemMap.containsKey(name.toLowerCase());
    }

    public static Item get(String name) {
        return itemMap.get(name.toLowerCase());
    }

    public static void put(String name, Item item) {
        itemMap.put(name.toLowerCase(), item);
    }

    public static void delete(String name) {
        itemMap.remove(name.toLowerCase());
    }

    public static Collection<Item> items() {
        return itemMap.values();
    }
}
