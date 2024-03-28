package recipeio.commands;

import recipeio.recipe.Recipe;

import java.util.ArrayList;

public class ListRecipeCommand {

    /**
     * Prints the recipes in the list.
     */
    public static void execute(ArrayList<Recipe> recipes) {
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
            System.out.println(output);
        }
    }
}
