package seedu.duke;

public class Drink {
    private String name;
    private int drinkVolume;
    private int caloriesPerMilliliter;
    private int carbsPerMilliliter;
    private int sugarPerMilliliter;
    private int proteinPerMilliliter;
    private int fatPerMilliliter;
    private int sodiumPerMilliliter;

    public Drink(String name, int volume, int calories, int carbs, int protein, int fat, int sugar, int sodium) {
        this.name = name;
        this.drinkVolume = volume;
        this.caloriesPerMilliliter = calories;
        this.carbsPerMilliliter = carbs;
        this.proteinPerMilliliter = protein;
        this.fatPerMilliliter = fat;
        this.sugarPerMilliliter = sugar;
        this.sodiumPerMilliliter = sodium;
    }

    public void infoDrink() {
        System.out.println("Drink: " + name);
        System.out.println("Volume: " + drinkVolume);
        System.out.println("Calories: " + getCalories());
        System.out.println("Carbs: " + getCarbs());
        // Sugar is part of Carbohydrates
        System.out.println("    Sugar: " + getSugar());
        System.out.println("Protein: " + getProtein());
        System.out.println("Fat: " + getFat());
        System.out.println("Sodium: " + getSodium());
    }

    public String getName() {
        return name;
    }

    public int getDrinkVolumeSize() {
        return drinkVolume;
    }

    public int getCalories() {
        return caloriesPerMilliliter * drinkVolume;
    }

    public int getCarbs() {
        return carbsPerMilliliter * drinkVolume;
    }

    public int getSugar() {
        return sugarPerMilliliter * drinkVolume;
    }

    public int getProtein() {
        return proteinPerMilliliter * drinkVolume;
    }

    public int getFat() {
        return fatPerMilliliter* drinkVolume;
    }

    public int getSodium() {
        return sodiumPerMilliliter * drinkVolume;
    }
}
