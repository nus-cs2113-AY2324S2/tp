package seedu.duke;

import java.util.ArrayList;

public class ResultsList {
    private static final int ZERO_RESULTS = 0;
    protected ArrayList<Results> sessionResults;
    protected ArrayList<Integer> topicsChosen;
    protected int count = ZERO_RESULTS;

    public ResultsList() {
        sessionResults = new ArrayList<>();
        topicsChosen = new ArrayList<>();
    }

    public void addResults(Results roundResults) {
        sessionResults.add(roundResults);
    }

    public void addQuestions(Integer topicNumber) {
        topicsChosen.add(topicNumber);
    }

    public Results getSpecifiedResult(int index) throws IndexOutOfBoundsException {
        return sessionResults.get(index);
    }

    public Integer getTopicNum(int index) throws IndexOutOfBoundsException {
        return topicsChosen.get(index);
    }

    public int getSizeOfAllResults() {
        return sessionResults.size();
    }

    public void clearResults() {
        sessionResults.clear();
        count = ZERO_RESULTS;
    }

    public int getNumOfResults() {
        return count;
    }
}
