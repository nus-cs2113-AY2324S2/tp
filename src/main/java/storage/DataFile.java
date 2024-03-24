package storage;

import java.nio.file.Files;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

import workouts.Workout;
import health.Health;
import utility.CustomExceptions;
import utility.UiConstant;

/**
 * Represents a DataFile object used to read and write data stored in PulsePilot to a file.
 */
public class DataFile {

    private static DataFile instance = null;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private DataFile() {
        initializeDataFile();
    }

    /**
     * Returns a singular instance of the DataFile class.
     * If the instance is null, it creates a new instance.
     *
     * @return An instance of the LogFile class.
     */
    public static DataFile getInstance() {
        if (instance == null) {
            instance = new DataFile();
        }
        return instance;
    }

    /**
     * Initialises the data file to be used. Function exits if file cannot be created.
     */
    public static void initializeDataFile() {
        File dataFile = new File(UiConstant.DATA_FILE_PATH);
        try {
            checkDataFile(dataFile);
        } catch (CustomExceptions.FileCreateError e) {
            System.err.println("Cannot create data file.");
            System.exit(1);
        }
        Path dataFilePath = Path.of(UiConstant.DATA_FILE_PATH);
        assert Files.exists(dataFilePath) : "Data file does not exist.";
    }

    /**
     * Checks if data file already exists. If it does, log it. Else, create the file and log the event.
     * @param dataFile Represents the data file.
     * @throws CustomExceptions.FileCreateError If there is an error creating the data file.
     */
    public static void checkDataFile(File dataFile) throws CustomExceptions.FileCreateError {
        try {
            if (dataFile.createNewFile()) {
                LogFile.writeLog("Created new data file", false);
            } else {
                LogFile.writeLog("Reading from existing data file", false);
            }
        } catch (IOException e) {
            throw new CustomExceptions.FileCreateError("Could not create data file.");
        }
    }

    /**
     * Writes health data to the data file.
     * @param healthData Health data to be written.
     */
    public static void writeHealthData(Health healthData) {
        // bmi format: bmi|HEIGHT|WEIGHT|BMI_SCORE|DATE (NA if no date)
        // period format: period|START|END|DURATION|NEXT
        // appointment format: appointment|DATE|DESCRIPTION
    }

    /**
     * Writes Workout data to the data file.
     * @param workoutData Workout data to be written.
     */
    public static void writeWorkoutData(Workout workoutData){
        // run format: run|DISTANCE|TIME|PACE|DATE
        /*
        Gym Format:
        gym|NUM_STATIONS|DATE|gym_1|STATION1_NAME|NUM_SETS|WEIGHT1,WEIGHT2,WEIGHT3,WEIGHT4
        |gym_2|STATION2_NAME...
         */
    }

    /**
     * Function reads the existing data file, and adds the relevant data to PulsePilot.
     * @throws CustomExceptions.FileReadError If there is an error reading the data file.
     */
    public static void readDataFile() throws CustomExceptions.FileReadError {
        int itemCount = 0; // just for getting lineNumber, no other use
        try {
            Scanner readFile = new Scanner(UiConstant.DATA_FILE_PATH);
            while (readFile.hasNext()) {
                String [] words = readFile.nextLine().split("\\|");
                switch (words[0].trim()){
                // case "macro":
                //     processMacro(words);
                //     break;

                // case "sleep":
                //     processSleep(words);
                //     break;

                // case "appointment":
                //     processAppointment(words);
                //     break;
                
                case "period":
                    // processPeriod(words);
                    break;

                case "bmi":
                    // processBmi(words);
                    break;

                case "gym":
                    // processGym(words);
                    break;

                case "run":
                    // processRun(words);
                    break;

                default: {
                    System.out.println("Invalid item at line: " + (itemCount + 1) + "!");
                    LogFile.writeLog("Invalid item read", true);
                }
                }
                itemCount += 1;
            }
        } catch (Exception e) {
            throw new CustomExceptions.FileReadError("Could not read data file contents");
        }
    }
    /*
    private Health health;

    public void save(int id, Health health) {
        assert health != null : "Health object cannot be null when saving";
        assert id >= 0 : "ID must be non-negative for saving";
        String fileName = "health_data_" + id + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            //bw.write("Height: " + Health.height);
            bw.newLine();
            //bw.write("Weight: " + Health.weight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataFile load(int id) {
        assert id >= 0 : "ID must be non-negative for loading";
        String fileName = "health_data_" + id + ".txt";
        DataFile dataFile = new DataFile();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataFile;
    }

    public static void readFile(String fileName) {
        assert fileName != null && !fileName.isEmpty() : "File name cannot be null or empty";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHealth(Health health) {
        assert health != null : "Cannot set a null Health object";
        this.health = health;

    }
    */


}

