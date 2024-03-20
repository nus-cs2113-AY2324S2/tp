package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStream;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Favourites> loadFavourites() throws FileNotFoundException {
        ArrayList<Favourites> loadedFavourites = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Favourites favourites = Parser.parseFavourites(line);
                if (favourites != null) {
                    loadedFavourites.add(favourites);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! No saved tasks found, starting with an empty list ~");
            new File(file.getParent()).mkdirs();
        }

        return loadedFavourites;
    }

    public void saveFavourites(ArrayList<Favourites> favourites) throws IOException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Favourites favourite: favourites) {
                writer.write(favourite.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    public ArrayList<Food> loadFood() throws FileNotFoundException {
        ArrayList<Food> loadedFood = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("FoodList.txt");
        if (is == null) {
            throw new FileNotFoundException("Food list file not found");
        }
        try (Scanner scanner = new Scanner(is)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Parse the line into a Food object and add it to the list
                loadedFood.add(Parser.parseFood(line));
            }
        }
        return loadedFood;
    }

    public ArrayList<Activity> loadActivity() throws FileNotFoundException {
        ArrayList<Activity> loadedActivity = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("ActivityList.txt");
        if (is == null) {
            throw new FileNotFoundException("Activity list file not found");
        }
        try (Scanner scanner = new Scanner(is)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Parse the line into an Activity object and add it to the list
                loadedActivity.add(Parser.parseActivity(line));
            }
        }
        return loadedActivity;
    }
}
