//@@author L5-Z
package storage;

import java.io.FileWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import health.Appointment;
import health.Bmi;
import health.Period;
import utility.ErrorConstant;
import workouts.Gym;
import workouts.Run;
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
        loadDataFile();
    }

    /**
     * Returns a singular instance of the DataFile class.
     * If the instance is null, it creates a new instance.
     *
     * @return An instance of the DataFile class.
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
    public static int loadDataFile() {
        File dataFile = new File(UiConstant.DATA_FILE_PATH);
        int status = UiConstant.FILE_NOT_FOUND;
        try {
            status = verifyIntegrity(dataFile);
        } catch (CustomExceptions.FileCreateError e) {
            System.err.println(ErrorConstant.CREATE_FILE_ERROR);
            LogFile.writeLog(ErrorConstant.CREATE_FILE_ERROR, true);
            System.exit(1);
        }
        Path dataFilePath = Path.of(UiConstant.DATA_FILE_PATH);
        assert Files.exists(dataFilePath) : "Data file does not exist.";
        return status;
    }

    /**
     * Checks if data file already exists. If it does, log it. Else, create the file and log the event.
     *
     * @param dataFile Represents the data file.
     * @return Integer representing a found data file, 0, or not found, 1
     * @throws CustomExceptions.FileCreateError If there is an error creating the data file.
     */
    public static int verifyIntegrity(File dataFile) throws CustomExceptions.FileCreateError {
        try {
            if (dataFile.createNewFile()) {
                LogFile.writeLog("Created new data file", false);
                return UiConstant.FILE_NOT_FOUND;
            } else {
                LogFile.writeLog("Reading from existing data file", false);
                return UiConstant.FILE_FOUND;
            }
        } catch (IOException e) {
            throw new CustomExceptions.FileCreateError(ErrorConstant.CREATE_FILE_ERROR);
        }
    }

    /**
     * Writes health data to the data file.
     *
     * // param healthData Health data to be written.
     */
    public static void writeHealthData(ArrayList<Bmi> bmiArrayList,
                                       ArrayList<Appointment> appointmentArrayList,
                                       ArrayList<Period> periodArrayList) {

        // Write each bmi entry in a specific format
        // bmi format: bmi|HEIGHT|WEIGHT|BMI_SCORE|DATE (NA if no date)
        for (Health bmiEntry : bmiArrayList) {
            // dataFile.write(task.getType() + UiConstant.LINE.trim() + task.getLabel() + UiConstant.LINE.trim()
            // + task.getRange() + UiConstant.LINE.trim() +
            //       task.getStatusIcon() + System.LineSeparator());
        }

        // Write each appointment entry in a specific format
        // appointment format: appointment|DATE|DESCRIPTION
        for (Health appointmentEntry : appointmentArrayList) {
        // dataFile.write(task.getType() + UiConstant.LINE.trim() + task.getLabel() + UiConstant.LINE.trim()
        // + task.getRange() + UiConstant.LINE.trim() +
        //       task.getStatusIcon() + System.LineSeparator());
        }

        // Write each period entry in a specific format
        // period format: period|START|END|DURATION|NEXT
        for (Health periodEntry : periodArrayList) {
            // dataFile.write(task.getType() + UiConstant.LINE.trim() + task.getLabel() + UiConstant.LINE.trim()
            // + task.getRange() + UiConstant.LINE.trim() +
            //       task.getStatusIcon() + System.LineSeparator());
        }
    }

    /**
     * Writes Workout data to the data file.
     * // param workoutData Workout data to be written.
     */
    public static void writeWorkoutData(ArrayList<Run> runArrayList, ArrayList<Gym> gymArrayList){

        // Write each period entry in a specific format
        // run format: run|DISTANCE|TIME|PACE|DATE
        for (Workout runEntry : runArrayList) {
            // dataFile.write(task.getType() + UiConstant.LINE.trim() + task.getLabel() + UiConstant.LINE.trim()
            // + task.getRange() + UiConstant.LINE.trim() +
            //       task.getStatusIcon() + System.LineSeparator());
        }

        // Write each period entry in a specific format
        /*
        Gym Format:
        gym|NUM_STATIONS|DATE|gym_1|STATION1_NAME|NUM_SETS|WEIGHT1,WEIGHT2,WEIGHT3,WEIGHT4
        |gym_2|STATION2_NAME...
         */
        for (Workout gymEntry : gymArrayList) {
            // dataFile.write(task.getType() + UiConstant.LINE.trim() + task.getLabel() + UiConstant.LINE.trim()
            // + task.getRange() + UiConstant.LINE.trim() +
            //       task.getStatusIcon() + System.LineSeparator());
        }
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
                String [] words = readFile.nextLine().split(UiConstant.LINE.trim());
                String dataType = words[0].trim();
                DataType filter = DataType.valueOf(dataType);
                switch (filter){

                case APPOINTMENT:
                    // processAppointment(words);
                    break;
                
                case PERIOD:
                    // processPeriod(words);
                    break;

                case BMI:
                    // processBmi(words);
                    break;

                case GYM:
                    // processGym(words);
                    break;

                case RUN:
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
            throw new CustomExceptions.FileReadError(ErrorConstant.CORRUPT_ERROR);
        }
    }

    /**
     * Saves the user data to a file.
     *
     * // param health data of all specified user input to be saved.
     * @throws CustomExceptions If an error occurs during file operations.
     */
    public static void saveDataFile(ArrayList<Bmi> bmiArrayList,
                                    ArrayList<Appointment> appointmentArrayList,
                                    ArrayList<Period> periodArrayList,
                                    ArrayList<Run> runArrayList,
                                    ArrayList<Gym> gymArrayList
                                    ) throws CustomExceptions.FileWriteError {

        try (FileWriter dataFile = new FileWriter(UiConstant.DATA_FILE_PATH)) {

            writeHealthData(bmiArrayList,
                    appointmentArrayList,
                    periodArrayList);

            writeWorkoutData(runArrayList, gymArrayList);

            dataFile.close();

        } catch (IOException e) {
            throw new CustomExceptions.FileWriteError(ErrorConstant.SAVE_ERROR);
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

