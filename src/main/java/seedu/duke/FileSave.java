package seedu.duke;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSave {
    private static Logger logger = Logger.getLogger("LoadFileLogger");
    private static String filePath;

    public FileSave(String path) {
        this.filePath = path;
    }

    public void loadFileContents(TravelActivityList list) throws FileNotFoundException {
        logger.log(Level.INFO, "loadFileContents");
        java.io.File f = new java.io.File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String[] line = s.nextLine().split(" / ");
            switch (line[0].toLowerCase()){
            case "accommodation":
                TravelActivity accommodation;
                if (line.length == 6) {
                    accommodation = new Accommodation(line[2], LocalDate.parse(line[3]), line[4], line[5].trim());
                } else {
                    accommodation = new Accommodation(line[2], LocalDate.parse(line[3]), line[4], "");
                }
                list.addTravelActivity(accommodation);
                if(line[1].equals("1")){
                    accommodation.setActivityStatus(true);
                }
                break;
            case "food":
                TravelActivity food;
                if (line.length == 6) {
                    food = new Food(line[2], LocalDate.parse(line[3]), line[4], line[5].trim());
                } else {
                    food = new Food(line[2], LocalDate.parse(line[3]), line[4], "");
                }

                list.addTravelActivity(food);
                if(line[1].equals("1")){
                    food.setActivityStatus(true);
                }
                break;
            case "landmark":
                TravelActivity landmark;
                if (line.length == 6) {
                    landmark = new Landmark(line[2], LocalDate.parse(line[3]), line[4], line[5].trim());
                } else {
                    landmark = new Landmark(line[2], LocalDate.parse(line[3]), line[4], "");
                }
                list.addTravelActivity(landmark);
                if(line[1].equals("1")){
                    landmark.setActivityStatus(true);
                }
                break;
            case "general":
                TravelActivity newActivity = new TravelActivity(line[2], LocalDate.parse(line[3]),
                                                                line[4], line[5].trim());
                list.addTravelActivity(newActivity);
                if(line[1].equals("1")){
                    newActivity.setActivityStatus(true);
                }
                break;

            default:
                throw new FileNotFoundException("File is corrupted or has invalid format");
            }
        }
    }

    public void saveActivityList(TravelActivityList list) throws IOException {
        logger.log(Level.INFO, "saveActivityList");
        FileWriter fw = new FileWriter(filePath);
        for (TravelActivity travelActivity: list.getTravelActivities()) {
            if (travelActivity instanceof Accommodation) {
                fw.write("accommodation / ");
            } else if (travelActivity instanceof Food) {
                fw.write("food / ");
            } else if (travelActivity instanceof Landmark) {
                fw.write("landmark / ");
            } else {
                fw.write("general / ");
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
        logger.log(Level.INFO, "readFile");
        try {
            loadFileContents(list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
