package seedu.duke;

import java.util.ArrayList;


public class RecipeList {
    /**
     * Represents the user's list of recipes (ie their recipe book).
     */
    public static final String SPACE = " ";
    private final ArrayList<Recipe> recipes;

    /**
     * Constructor for RecipeList.
     */
    public RecipeList() {
        recipes = new ArrayList<>();
    }

    /**
     * Deletes the Recipe from the recipe list.
     *
     * @param input The input from the user.
     */
    public void deleteRecipe(String input) {
        int taskNumber = Integer.parseInt(input.split(SPACE)[1]) - 1;
        if (taskNumber >= tasks.size() || taskNumber < 0) {
            ui.noValidNumberPrinter(input);
        } else {
            Task selectedTask = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            ui.deleteTaskPrinter(selectedTask, tasks);
        }
    }

    /**
     * Adds a recipe to the list.
     *
     * @param newTask The new recipe to be added.
     */
    public void addTask(Recipe recipe) {
        recipes.add(recipe);
    }

    /**
     * Returns the size of the list (recipe book).
     *
     * @return The size of the list (recipe book).
     */
    public int getSize() {
        return recipes.size();
    }

    /**
     * Returns the recipe at the specified index.
     *
     * @param index The index of the task.
     * @return The recipe at the specified index.
     */
    public Recipe get(int index) {
        return recipes.get(index);
    }

    /**
     * Prints the recipes in the list.
     */
    public void printRecipes() {
        if (recipes.isEmpty()) {
            System.out.println("Sorry, there are no recipes in your recipe book to print.");
        } else {
            int counter = 0;
            StringBuilder output = new StringBuilder();
            while (counter < recipes.size()) {
                String recipeNumber = Integer.toString(counter + 1);
                Recipe selected = recipes.get(counter);
                output.append(selected.toString());
                if (counter != recipes.size() - 1) {
                    output.append("\n");
                }
                counter += 1;
            }
            System.out.println(output.toString());
        }
    }

    /**
     * Finds the recipes with key name in recipe book.
     *
     * @param keyword The keyword that the user passes in.
     */
    public void findRecipe(String keyword) {
        RecipeList matches = new RecipeList();
        if (recipes.isEmpty()) {
            System.out.println("Sorry, you have no recipes to find matches with. Try adding some!");
        } else {
            for (int i = 0; i < recipes.size(); i += 1) {
                if (recipes.get(i).getName().contains(keyword)) {
                    matches.addRecipe(recipes.get(i));
                }
            }
            if (matches.getSize() == 0) {
                System.out.println("There were no matches. Try searching for something else.");
            } else {
                int counter = 0;
                StringBuilder output = new StringBuilder();
                while (counter < matches.getSize()) {
                    String recipeNumber = Integer.toString(counter + 1);
                    Recipe selected = matches.get(counter);
                    output.append(selected.toString());
                    if (counter != matches.getSize() - 1) {
                        output.append("\n");
                    }
                    counter += 1;
                }
                System.out.println("Here are your matches:\n" + output);
            }
        }
        }
    }
}
