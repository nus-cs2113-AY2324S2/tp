package seedu.duke;

public class Meal {
    private String name;
    private int caloriesPerServing;
    private int carbsPerServing;
    private int proteinPerServing;
    private int fatPerServing;
    private int fiberPerServing;
    private int sugarPerServing;
    private int servingSize;

    // Constructor
    public Meal(String name, int servingSize, int caloriesPerServing, int carbs, int protein, int fat, int fiber, int sugar) {
        this.name = name;
        this.caloriesPerServing = caloriesPerServing;
        this.carbsPerServing = carbs;
        this.proteinPerServing = protein;
        this.fatPerServing = fat;
        this.fiberPerServing = fiber;
        this.sugarPerServing = sugar;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getServingSize() {
        return servingSize;
    }

    public int getCalories() {
        return caloriesPerServing * servingSize;
    }

    public int getCarbs() {
        return carbsPerServing * servingSize;
    }

    public int getProtein() {
        return proteinPerServing * servingSize;
    }

    public int getFat() {
        return fatPerServing * servingSize;
    }

    public int getFiber() {
        return fiberPerServing * servingSize;
    }

    public int getSugar() {
        return sugarPerServing * servingSize;
    }

}
