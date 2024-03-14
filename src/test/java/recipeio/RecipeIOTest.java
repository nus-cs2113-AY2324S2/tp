package recipeio;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import recipeio.enums.MealCategory;
import recipeio.recipe.Recipe;

class RecipeIOTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testStringConversion() {
        Recipe testRecipe = new Recipe("Spaghetti Carbonara", 0, 0, null,
                MealCategory.LUNCH, null);
        assertEquals("Spaghetti Carbonara / LUNCH", testRecipe.toString());
    }
}
