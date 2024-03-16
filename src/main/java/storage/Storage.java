package storage;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.util.ArrayList;

import recipeio.enums.MealCategory;
import recipeio.recipe.Recipe;
import recipeio.recipe.RecipeList;

public class Storage {
    public static final String FILE_PATH = "recipe.txt";

    public static final String DELIMITER = " | ";
    /**
     * Save the list of items to a txt file
     *
     * @throws IOException for any issues saving list to txt file
     */
    public static void saveFile(RecipeList recipeList) throws IOException {
        File myFile = new File(FILE_PATH);
        FileWriter fw = new FileWriter(myFile, false);
        BufferedWriter bw = new BufferedWriter(fw);

        int RecipeSize = recipeList.getSize();
        for (int i = 0; i < RecipeSize; i++) {
            Recipe recipe = recipeList.get(i);
            bw.write(recipe.name);
            bw.write(DELIMITER);
            bw.write(String.valueOf(recipe.cookTime));
            bw.write(DELIMITER);
            bw.write(String.valueOf(recipe.calories));
            bw.write(DELIMITER);
            bw.write(String.join(",",recipe.allergies));
            bw.write(DELIMITER);
            bw.write(recipe.category.toString());
            bw.write(DELIMITER);
            bw.write(recipe.url);
        }
        bw.newLine();
        bw.close();
    }

    /**
     * Loads the text file into a recipeList
     *
     * @throws FileNotFoundException if file cannot be found
     */
    public static void loadFile(RecipeList recipeList) throws FileNotFoundException {
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String line = s.nextLine();
            String name = line.split(" \\| ")[0];
            int cookTime = Integer.parseInt(line.split(" \\| ")[1]);
            int calories = Integer.parseInt(line.split(" \\| ")[2]);
            ArrayList<String> allergies = new ArrayList<>();
            allergies.add(line.split(" \\| ")[3]);
            MealCategory category = MealCategory.valueOf(line.split(" \\| ")[4]);
            String url = line.split(" \\| ")[5];
            Recipe testRecipe = new Recipe(name, cookTime, calories, allergies, category, url);
            recipeList.addRecipe(testRecipe);
        }
    }
}
