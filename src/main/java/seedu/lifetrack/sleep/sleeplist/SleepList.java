package seedu.lifetrack.sleep.sleeplist;

import seedu.lifetrack.sleep.Sleep;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserSleep;
import seedu.lifetrack.ui.SleepListUi;
import java.util.ArrayList;
import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectSleepInputMessage;
import static seedu.lifetrack.ui.SleepListUi.deleteMessage;
import static seedu.lifetrack.ui.SleepListUi.sleepListHeader;

public class SleepList {
    private ArrayList<Sleep> sleepList;
    public SleepList() {
        this.sleepList=new ArrayList<>();
    }

    public Sleep getSleep(int index) {
        assert index >= 0 && index < sleepList.size() : "Index out of bounds";
        return sleepList.get(index);
    }
    public void addSleep(String input) {
        try {
            Sleep newSleep = ParserSleep.parseSleepInput(input);
            sleepList.add(newSleep);
            SleepListUi.addEntryMessage();
        } catch (InvalidInputException e) {
            System.out.println(getIncorrectSleepInputMessage());
        }
    }
    public void deleteSleep(String line) {
        try {
            int index = Integer.parseInt(line.split(" ")[2]) ;
            sleepList.remove(index - 1);
            deleteMessage();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(SleepListUi.deleteLogIndexMessage());
        } catch (NumberFormatException e) {
            System.out.println(SleepListUi.deleteLogNumberMessage());
        }
    }
    public void printSleepList() {
        if (this.sleepList.isEmpty()) {
            SleepListUi.emptyListMessage();
        } else {
            sleepListHeader();
            for (int i = 0; i < this.sleepList.size(); i++) {
                System.out.println("\t " + (i + 1)+ ". " + getSleep(i).toString());
            }
        }
    }
    public int getSize() {
        return sleepList.size();
    }


}
