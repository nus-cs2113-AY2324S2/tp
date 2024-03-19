package recipeio.commands;

import recipeio.recipe.Recipe;

import java.util.ArrayList;

public class FindByNameCommand {

    /**
     * Finds the recipes with key name in recipe book.
     *
     * @param keyword The keyword that the user passes in.
     */
    public void execute(String keyword, ArrayList<Recipe> recipes) {
        ArrayList<Recipe> matches = new ArrayList<>();
        if (recipes.isEmpty()) {
            System.out.println("Sorry, you have no recipes to find matches with. Try adding some!");
        } else {
            for (Recipe recipe : recipes) {
                if (recipe.name.contains(keyword)) {
                    matches.add(recipe);
                }
            }
            if (matches.isEmpty()) {
                System.out.println("There were no matches. Try searching for something else.");
            } else {
                int counter = 0;
                StringBuilder output = new StringBuilder();
                while (counter < matches.size()) {
                    String recipeNumber = Integer.toString(counter + 1);
                    Recipe selected = matches.get(counter);
                    output.append(selected.toString());
                    if (counter != matches.size() - 1) {
                        output.append("\n");
                    }
                    counter += 1;
                }
                System.out.println("Here are your matches:\n" + output);
            }
        }
    }

}
