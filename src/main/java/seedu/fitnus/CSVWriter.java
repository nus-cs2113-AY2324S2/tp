package seedu.fitnus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVWriter {
    public static void main(String[] args) {
        writeIntoFile("hi", "FOOD");
    }
    public static void writeIntoFile(String foodItem, String fileName) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        File file = null;
        if (fileName.toLowerCase().contains("food")) {
            file = new File("./db/Output_Food_" + df.format(new Date())+".csv");
        } else {
            file = new File("./db/Output_Drink_" + df.format(new Date())+".csv");
        }
        if (!file.exists()) {
            try{
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("error with creating a file");
            }
        }
        try (FileWriter fw = new FileWriter(file)){
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(foodItem);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("error trying to add something into the file");
        }
    }
}
