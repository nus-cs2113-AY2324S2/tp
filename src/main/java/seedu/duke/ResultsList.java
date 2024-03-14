package seedu.duke;

import java.util.ArrayList;

public class ResultsList {
    protected static ArrayList<Results> sessionResults;

    public ResultsList() {
        sessionResults = new ArrayList<>();
    }

    public static void addResult(Results roundResults) {
        sessionResults.add(roundResults);
    }
    
    public static Results getSpecifiedResult(int index) {
        return sessionResults.get(index);
    }

    public static ArrayList<Results> getAllResults() {
        return sessionResults;
    }
}

