package recipeio.commands;

import recipeio.recipe.Recipe;

import java.util.ArrayList;

public class FindByAllergyCommand {

    /**
     * Returns a list of recipes with the allergy included
     *
     * @param allergy The allergy that the user is trying to filter by
     */
    public static String execute(String allergy, ArrayList<Recipe> recipes) {
        int count = 0;
        String output = "";
        for (Recipe item: recipes) {
            for (String value : item.allergies) {
                if (value.contains(allergy)) {
                    output = "List of recipes with " + allergy + " mentioned:\n" + item.name + "\n";
                    count++;
                }
            }
        }
        //if no allergies are found
        if (count == 0) {
            output = "There are no recipes with " + allergy;
        }
        return output;
    }
}
