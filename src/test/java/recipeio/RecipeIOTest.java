package recipeio;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import recipeio.enums.MealCategory;
import recipeio.recipe.Recipe;
import recipeio.recipe.RecipeList;

import java.util.ArrayList;

class RecipeIOTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testAddRecipe() {
        RecipeList recipes = new RecipeList();
        ArrayList allergies = new ArrayList<String>();
        allergies.add("eggs");
        Recipe newRecipe = new Recipe("cookies", 40, 350, allergies,
                MealCategory.DESSERT, "THIS IS MY URL");
        recipes.addRecipe(newRecipe);
        assertTrue(recipes.getSize() == 1);
    }

    @Test
    public void testDeleteRecipe() {
        RecipeList recipes = new RecipeList();
        ArrayList allergies = new ArrayList<String>();
        allergies.add("eggs");
        Recipe newRecipe = new Recipe("cookies", 40, 350, allergies,
                MealCategory.DESSERT, "THIS IS MY URL");
        recipes.addRecipe(newRecipe);
        recipes.deleteRecipe(1);
        assertTrue(recipes.getSize() == 0);
    }
}
