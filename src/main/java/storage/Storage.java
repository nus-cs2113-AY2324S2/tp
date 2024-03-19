package storage;

import seedu.duke.ExpenditureList;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    private static final String EXPENDITURE_FILE_PATH = "./data/expenditure.txt";

    private static void createNewFile() {
        File file = new File(EXPENDITURE_FILE_PATH);
        File directory = new File(file.getParent());
        try {
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    System.out.println("Failed to create directory: " + directory.getAbsolutePath());
                }
            }
            if (!file.createNewFile()) {
                System.out.println("Failed to create new file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error creating new file: " + e.getMessage());
        }
    }

    public static ExpenditureList readExpenditureFile() {
        ExpenditureList expenses = new ExpenditureList();
        File file = new File(EXPENDITURE_FILE_PATH);
        if (!file.exists()) {
            createNewFile();
            return expenses;
        }
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String expenditure = processLine(line);
                ExpenditureList.addExpenditure(expenditure,false);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        }
        return expenses;
    }

    private static String processLine(String line) {
        String[] parts = line.split("\\|");
        return ("d/ " + parts[0] + "amt/" + parts[1] + "date/" + parts[2]);
    }

    public static void writeToFile(ExpenditureList expenses) {
        try {
            PrintWriter fw = new PrintWriter(EXPENDITURE_FILE_PATH);
            for (int i = 0; i < ExpenditureList.expenditureCount; i++) {
                fw.println(expenses.getExpenditure(i).toStringStorage());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file" + e.getMessage());
        }
    }

}
