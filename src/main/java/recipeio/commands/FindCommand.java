package recipeio.commands;

import recipeio.recipe.Recipe;

import java.time.LocalDate;
import java.util.ArrayList;

public class FindCommand {
    public static void findKeyword(String keyword, ArrayList<Recipe> recipes) {
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

    public static void findDate(LocalDate date, ArrayList<Recipe> recipes) {
        ArrayList<Recipe> matches = new ArrayList<>();
        if (recipes.isEmpty()) {
            System.out.println("Sorry, you have no recipes to find matches with. Try adding some!");
        } else {
            for (Recipe recipe : recipes) {
                if (recipe.dateAdded.isEqual(date)) {
                    matches.add(recipe);
                }
            }
            if (matches.isEmpty()) {
                System.out.println("There were no matches. Try searching for something else.");
            } else { //can convert to recipelist printer method
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

    public static void execute(String userInput, ArrayList<Recipe> recipes) {
        String[] inputSplitUp = userInput.split(" ", 3);
        String findType = inputSplitUp[1];
        switch (findType) {
        case ("kw"):
            String keyword = inputSplitUp[2];
            findKeyword(keyword, recipes);
            break;
        case ("date"):
            LocalDate date = LocalDate.parse(inputSplitUp[2]);
            findDate(date, recipes);
            break;
        default:
            System.out.println("Sorry. Please follow one of the find command formats");
        }
    }
}

