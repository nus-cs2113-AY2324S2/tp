package florizz.core;

import java.util.ArrayList;
import florizz.objects.Flower;

/**
 * A class that contains a dictionary of preset flowers
 */
public class FlowerDictionary {
    private static ArrayList<Flower> flowerDict = new ArrayList<Flower>();

    /**
     * Adds a new flower to the flower dictionary
     *
     * @param name Name of flower to be added
     * @param colour Colour of flower to be added
     * @param occasion Occasion that the flower is bought for
     */
    public static void add(String name, String colour, String occasion) {
        flowerDict.add(new Flower(name, colour, occasion));
    }

    /**
     * Adds flowers to the dictionary when florizz starts up (temporary)
     */
    public static void startup() {
        add("Orchid", "White", "Wedding");
        add("Rose", "Red", "Valentines");
        add("Lily", "White", "Funeral");
        add("Daisy", "White", "Valentines");
        add("Babys Breath", "White", "Wedding");
        add("Chrysanthemum", "White", "Funeral");
    }

    /**
     * Returns the size of flower dictionary
     *
     * @return The size as an int
     */
    public static int size() {
        return flowerDict.size();
    }

    /**
     * Gets the flower at the selected index in the flower dictionary
     *
     * @param i Index to get flower from
     * @return The flower object at index i
     */
    public static Flower get(int i) {
        return flowerDict.get(i);
    }
}
