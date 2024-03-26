//@@author L5-Z
package storage;

import java.io.FileWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import health.Appointment;
import health.Bmi;
import health.Period;
import utility.ErrorConstant;
import workouts.Gym;
import workouts.Run;
import utility.CustomExceptions;
import utility.UiConstant;

/**
 * Represents a DataFile object used to read and write data stored in PulsePilot to a file.
 */
public class DataFile {

    public static String userName = null;
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
     * Function reads the existing data file, and adds the relevant data to PulsePilot.
     * @throws CustomExceptions.FileReadError If there is an error reading the data file.
     */
    public static void readDataFile() throws CustomExceptions.FileReadError {
        int lineNumberCount = 0; // just for getting lineNumber, no other use
        try (final Scanner readFile = new Scanner(UiConstant.DATA_FILE_PATH)) {
            LogFile.writeLog("Read begins", false);

            while (readFile.hasNextLine()) {
                String [] input = readFile.nextLine().split(UiConstant.SPLIT_BY_LINE);
                LogFile.writeLog("Input: " + Arrays.toString(input), false);

                String dataType = input[UiConstant.DATA_TYPE_INDEX].trim();
                LogFile.writeLog("Datatype: " + dataType, false);
                String name = input[UiConstant.NAME_INDEX].trim();

                DataType filter = DataType.valueOf(dataType);
                switch (filter){

                case NAME:
                    processName(name);
                    break;

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

                default:
                    break; // valueOf results in immediate exception for non-match with enum DataType
                }
                lineNumberCount += 1;
            }
        } catch (Exception e) {
            LogFile.writeLog("Invalid item read at line: " + (lineNumberCount + 1) + "! " + e, true);
            throw new CustomExceptions.FileReadError(ErrorConstant.CORRUPT_ERROR);
        }
    }
    public static void processName(String name){
        userName = name.trim();
    }

    public static void processAppointment(){}
    public static void processPeriod(){}
    public static void processBmi(){}
    public static void processGym(){}
    public static void processRun(){}

    /**
     * Saves the user data to a file.
     *
     * // param health data of all specified user input to be saved.
     * @throws CustomExceptions If an error occurs during file operations.
     */
    public static void saveDataFile(String name,
                                    ArrayList<Bmi> bmiArrayList,
                                    ArrayList<Appointment> appointmentArrayList,
                                    ArrayList<Period> periodArrayList,
                                    ArrayList<Run> runArrayList,
                                    ArrayList<Gym> gymArrayList
                                    ) throws CustomExceptions.FileWriteError {

        try (FileWriter dataFile = new FileWriter(UiConstant.DATA_FILE_PATH)) {
            LogFile.writeLog("Attempting to write data, name: " + name, false);
            writeName(dataFile, name);
            LogFile.writeLog("Written name", false);
            writeHealthData(bmiArrayList,
                    appointmentArrayList,
                    periodArrayList);

            writeWorkoutData(runArrayList, gymArrayList);

            LogFile.writeLog("Write end", false);
            dataFile.close();

        } catch (IOException e) {
            throw new CustomExceptions.FileWriteError(ErrorConstant.SAVE_ERROR);
        }
    }

    /**
     * Writes health data to the data file.
     *
     * // param healthData Health data to be written.
     */
    public static void writeName(FileWriter dataFile, String name) throws IOException {
        dataFile.write(DataType.NAME + UiConstant.LINE.trim() + name.trim());
        LogFile.writeLog("Wrote name to file", false);
    }

    /**
     * Writes health data to the data file.
     *
     * // param healthData Health data to be written.
     */
    public static void writeHealthData(ArrayList<Bmi> bmiArrayList,
                                       ArrayList<Appointment> appointmentArrayList,
                                       ArrayList<Period> periodArrayList) {
        /*
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

         */
    }

    /**
     * Writes Workout data to the data file.
     * // param workoutData Workout data to be written.
     */
    public static void writeWorkoutData(ArrayList<Run> runArrayList, ArrayList<Gym> gymArrayList){
        /*
        // Write each period entry in a specific format
        // run format: run|DISTANCE|TIME|PACE|DATE
        for (Workout runEntry : runArrayList) {
            // dataFile.write(task.getType() + UiConstant.LINE.trim() + task.getLabel() + UiConstant.LINE.trim()
            // + task.getRange() + UiConstant.LINE.trim() +
            //       task.getStatusIcon() + System.LineSeparator());
        }

        // Write each period entry in a specific format

        Gym Format:
        gym|NUM_STATIONS|DATE|gym_1|STATION1_NAME|NUM_SETS|WEIGHT1,WEIGHT2,WEIGHT3,WEIGHT4
        |gym_2|STATION2_NAME...

        for (Workout gymEntry : gymArrayList) {
            // dataFile.write(task.getType() + UiConstant.LINE.trim() + task.getLabel() + UiConstant.LINE.trim()
            // + task.getRange() + UiConstant.LINE.trim() +
            //       task.getStatusIcon() + System.LineSeparator());
        }
        */
    }

}

