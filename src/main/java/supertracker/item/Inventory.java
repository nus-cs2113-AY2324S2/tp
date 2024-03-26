package supertracker.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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

    public static void clear() {
        itemMap.clear();
    }

    public static List<Item> getItems() {
        Collection<Item> items = itemMap.values();
        return new ArrayList<>(items);
    }
}
