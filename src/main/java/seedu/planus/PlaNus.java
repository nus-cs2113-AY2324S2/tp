package seedu.planus;

/**
 * The main class that represents the PlaNus application.
 */
public class PlaNus {

    private Timetable timetable;
    private boolean isExit;

    /**
     * Constructs a new PlaNus object.
     */
    public PlaNus() {
        isExit = false;
    }

    /**
     * Runs the PlaNus application.
     */
    public void run() {
        Ui.printLogo();

        timetable = Storage.loadTimetable(Storage.USER_TIMETABLE_FILE_NAME);

        while (!isExit) {
            String line = Ui.getUserCommand();
            try {
                isExit = Parser.parseCommand(line, timetable);
            } catch (Exception e) {
                Ui.printErrorMessage(e.getMessage());
            }
        }
        Ui.printExit();
    }
    /**
     * Main entry-point for the java.planus.PlaNus application.
     */
    public static void main(String[] args) {
        new PlaNus().run();
    }
}
