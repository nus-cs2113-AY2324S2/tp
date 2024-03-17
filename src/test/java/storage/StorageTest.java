package storage;
import org.junit.jupiter.api.Test;
import recipeio.recipe.RecipeList;
import recipeio.storage.Storage;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class StorageTest {
    public static final String FILE_PATH = "recipe.txt";
    @Test
    public void testExist(){
        File f = new File(FILE_PATH);
        if (f.exists()) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    public void testSaving(){
        RecipeList testRecipeList = new RecipeList();
        try {
            Storage.loadFile(testRecipeList);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }
}
