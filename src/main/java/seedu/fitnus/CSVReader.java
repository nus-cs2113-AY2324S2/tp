package seedu.fitnus;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static final String DELIMITER = ",";
    public static void main(String[] args){
        String csvFile = "./db/Meal_db.csv";
        CSVReader.read(csvFile);
    }
    public static void read(String filename) {
        try{
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(DELIMITER);
                for (String tempStr : tempArr) {
                    System.out.print(tempStr + " ");
                }
                System.out.println();
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
