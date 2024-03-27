package seedu.lifetrack.system.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.Food;
import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;
import seedu.lifetrack.liquids.liquidlist.LiquidEntry;
import seedu.lifetrack.sleep.sleeplist.SleepEntry;
import seedu.lifetrack.system.exceptions.ErrorMessages;

public class FileHandler {

    //general list constants
    private static final int DATE_INDEX = 0;
    private static final int DESCRIPTION_INDEX = 1;

    //calorie list constants
    private static final int ENTRY_TYPE_INDEX = 2;
    private static final int CALORIES_INDEX = 3;
    private static final int CARBOHYDRATES_INDEX = 4;
    private static final int PROTEINS_INDEX = 5;
    private static final int FATS_INDEX = 6;

    //liquids list constants
    private static final int VOLUME_INDEX = 2;

    //sleep list constants
    private static final int DURATION_INDEX = 2;

    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void writeEntries(ArrayList<Entry> entries) {
        try {
            String newData = "";
            for (Entry entry : entries) {
                newData += entry.toFileFriendlyString() + System.lineSeparator();
            }
            writeToFile(newData);
        } catch (IOException e) {
            System.out.println(ErrorMessages.getIOExceptionMessage());
        }
    }

    public ArrayList<Entry> getCalorieEntriesFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Entry> entries = new ArrayList<>();
        String line = "";
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            String date = words[DATE_INDEX];
            String description = words[DESCRIPTION_INDEX];
            int calories = Integer.parseInt(words[CALORIES_INDEX]);
            String entryType = words[ENTRY_TYPE_INDEX];
            if (entryType.equals("C_IN") && words.length == 5) {
                int carbohydrates = Integer.parseInt(words[CARBOHYDRATES_INDEX]);
                int proteins = Integer.parseInt(words[PROTEINS_INDEX]);
                int fats = Integer.parseInt(words[FATS_INDEX]);
                Food food = new Food(carbohydrates, proteins, fats);
                entries.add(new InputEntry(description, calories, date, food));
            } else if (entryType.equals("C_IN")) {
                entries.add(new InputEntry(description, calories, date));
            } else {
                entries.add(new OutputEntry(description, calories, date));
            }
        }
        return entries;
    }

    public ArrayList<Entry> getLiquidEntriesFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Entry> entries = new ArrayList<>();
        String line = "";
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            String date = words[DATE_INDEX];
            String description = words[DESCRIPTION_INDEX];
            int volume = Integer.parseInt(words[VOLUME_INDEX]);
            entries.add(new LiquidEntry(description, volume, date));
        }
        return entries;
    }

    public ArrayList<Entry> getSleepEntriesFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Entry> entries = new ArrayList<>();
        String line = "";
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            String date = words[DATE_INDEX];
            double duration = Double.parseDouble(words[DURATION_INDEX]);
            entries.add(new SleepEntry(duration, date));
        }
        return entries;
    }
}
