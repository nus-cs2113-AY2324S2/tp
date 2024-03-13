package seedu.duke;

import java.util.ArrayList;

public class ExpenditureList {
    private ArrayList<ExpenditureList> expenditureList;
    private int expenditureCount;

    public ExpenditureList() {
        this.expenditureList = new ArrayList<>();
        this.expenditureCount = 0;
    }
}
