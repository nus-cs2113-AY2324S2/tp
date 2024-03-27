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
     * @param occasions Occasions that the flower can be bought for
     */
    private static void add(String name, String colour, String[] occasions, String price) {

        Flower.Colour colourEnum = Flower.Colour.valueOf(colour.replaceAll(" ", "_").toUpperCase());
        ArrayList<Flower.Occasion> occasionsArrayList = new ArrayList<>();
        for (String occasion : occasions){
            occasionsArrayList.add(Flower.Occasion.valueOf(occasion.replaceAll(" ", "_").toUpperCase()));
        }
        flowerDict.add(new Flower(name, colourEnum, occasionsArrayList, price));
    }

    /**
     * Adds flowers to the dictionary when florizz starts up (temporary)
     */
    public static void startup() {
        add("Orchid", "White", new String[]{"Wedding"}, "$10.00");
        add("Rose", "Red", new String[]{"Valentines", "Wedding"}, "$2.00");
        add("Lily", "White", new String[]{"Funeral"}, "$2.50");
        add("Daisy", "White", new String[]{"Valentines"}, "$0.50");
        add("Baby Breath", "White", new String[] {"Wedding"}, "$1.00");
        add("Chrysanthemum", "White", new String[]{"Funeral"}, "$1.00");
        add("Hydrangea", "Blue", new String[] {"Wedding"}, "$9.00");
        add("Carnation", "Pink", new String[] {"Mothers Day"}, "$2.00");
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

    public static ArrayList<Flower> filterByOccasion(Flower.Occasion occasion){
        ArrayList<Flower> filteredFlowers = new ArrayList<>();
        for (int i = 0; i < flowerDict.size(); i++) {
            if (flowerDict.get(i).getOccasion().contains(occasion)){
                filteredFlowers.add(flowerDict.get(i));
            }
        }
        return filteredFlowers;
    }
}
