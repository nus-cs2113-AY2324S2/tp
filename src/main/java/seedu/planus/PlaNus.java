package seedu.planus;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PlaNus {

    private Timetable timetable;
    private boolean isExit;

    public PlaNus() {
        isExit = false;
    }

    public void run() {
        //ui start message
        Path filePath = Paths.get(Storage.USER_TIMETABLE_FILE_PATH);

        if (Files.exists(filePath)) {
            timetable = Storage.loadTimetable("myTimetable");
        }
        else {
            timetable = Parser.initialiseTimetable();
            Storage.writeToFile(timetable);
        }

        while (!isExit) {
            // ui get command String line = Ui.getUserCommand();
            //isExit = Parser.parseCommand(line, timetable);
            Storage.writeToFile(timetable);
        }
        //ui end message
    }
    /**
     * Main entry-point for the java.planus.PlaNus application.
     */
    public static void main(String[] args) {
        new PlaNus().run();
    }
}
