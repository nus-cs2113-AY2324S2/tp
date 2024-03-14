package seedu.brokeculator;

public class Logic {
    public Logic() {
        GeneralInputParser mainParser = new GeneralInputParser();
    }
    public void run() {
        while (true) {
            Command command = mainParser.getCommandFromUserInput();
            command.execute();
        }
    }
}
