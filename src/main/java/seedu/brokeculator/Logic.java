package seedu.brokeculator;

public class Logic {
    public Logic() {
        GeneralInputParser mainParser = new GeneralInputParser();
        FileManager fileManager = new FileManager();
    }
    public void run() {
        while (true) {
            try {
                Command command = mainParser.getCommandFromUserInput();
                command.execute();
                fileMager.save();
            } catch (Exception e) {
                UI.print("error occured, sus. " + e.getMessage());
            }
        }
    }
    private void loadTasksFromFile() {

    }
}
