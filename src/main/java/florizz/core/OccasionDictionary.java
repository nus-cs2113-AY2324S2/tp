package florizz.core;

import java.util.HashSet;


public class OccasionDictionary {
    private static HashSet<String> uniqueOccasions = new HashSet<>();

    public static void add(String Occasion) {
        uniqueOccasions.add(Occasion);
    }

    public static void startup() {
        for (int i = 0; i < FlowerDictionary.size(); i++) {
            add(FlowerDictionary.get(i).getOccasion().toLowerCase());
        }
    }

    public static int size() {
        return uniqueOccasions.size();
    }

    public static void print() {
        for (String occasion : uniqueOccasions) {
            System.out.println(occasion);
        }
    }

    public static boolean contains(String occasion) {
        return uniqueOccasions.contains(occasion.toLowerCase());
    }
}
