package seedu.duke;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSave {

    private static String filePath;

    public FileSave(String path) {
        this.filePath = path;
    }

    private static Logger logger = Logger.getLogger("LoadFileLogger");

    public void loadFileContents(TravelActivityList list) throws FileNotFoundException {
        java.io.File f = new java.io.File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String[] line = s.nextLine().split(" / ");
            //logger.log(Level.INFO, line[0] + "/ /" + line[1] + "/ /" + line[2] + "/ /" + line[3] + "/ /" + line[4] + "/ /" + line[5]);

            switch (line[0].toLowerCase()){
            case "accommodation":
                TravelActivity accommodation = new Accommodation(line[2], LocalDate.parse(line[3]), line[4], line[5].trim());
                list.addTravelActivity(accommodation);
                if(line[1].equals(" 1 ")){
                    accommodation.setActivityStatus(true);
                }
                break;
            case "food":
                TravelActivity food = new Food(line[2], LocalDate.parse(line[3]), line[4], line[5].trim());
                list.addTravelActivity(food);
                if(line[1].equals(" 1 ")){
                    food.setActivityStatus(true);
                }
                break;
            case "landmark":
                TravelActivity landmark = new Landmark(line[2], LocalDate.parse(line[3]), line[4], line[5].trim());
                list.addTravelActivity(landmark);
                if(line[1].equals(" 1 ")){
                    landmark.setActivityStatus(true);
                }
                break;
            default:
                throw new FileNotFoundException("File is corrupted or has invalid format");
            }
        }
    }

    public void saveActivityList(TravelActivityList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (TravelActivity travelActivity: list.getTravelActivities()) {
            if (travelActivity instanceof Accommodation) {
                fw.write("accommodation / ");
            } else if (travelActivity instanceof Food) {
                fw.write("food / ");
            } else if (travelActivity instanceof Landmark) {
                fw.write("landmark / ");
            }
            fw.write((travelActivity.getActivityStatus() ? "1 / " : "0 / ") + travelActivity.getPlan()
                    + " / " + travelActivity.getDate()
                    + " / " + travelActivity.getDuration()
                    + " / " + travelActivity.getTag()
                    + System.lineSeparator());
        }
        fw.close();
    }

    public void readFile(TravelActivityList list) {
        try {
            loadFileContents(list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
