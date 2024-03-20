package seedu.duke;

import seedu.duke.exceptions.CustomException;

import java.util.ArrayList;

public class ResultsList {
    private static final int ZERO_RESULTS = 0;
    private static final String MESSAGE_NO_RESULTS = "No results yet.";

    protected ArrayList<Results> sessionResults;
    protected ArrayList<QuestionsList> sessionQuestions;
    protected int count;

    public ResultsList() {
        sessionResults = new ArrayList<>();
        sessionQuestions = new ArrayList<>();
        count = ZERO_RESULTS;
    }

    public void addResultAndQuestions(Results roundResults, QuestionsList roundQuestions) {
        sessionResults.add(roundResults);
        sessionQuestions.add(roundQuestions);
        count++;
    }

    public Results getSpecifiedResult(int index) throws IndexOutOfBoundsException {
        return sessionResults.get(index);
    }

    public ArrayList<Results> getAllResults() {
        return sessionResults;
    }

    public String toString(boolean includesQuestion) throws CustomException {
        if (sessionResults.isEmpty()) {
            throw new CustomException(MESSAGE_NO_RESULTS);
        }
        StringBuilder listOfResults = new StringBuilder();
        for (int i = 0; i < count; i++) {
            listOfResults.append((i + 1)).append(". ").append(sessionResults.get(i).getScore()).append("\n");
            if (includesQuestion) {
                listOfResults.append(sessionQuestions.get(i).getAllQuestions());
            }
        }
        return listOfResults.toString();
    }
}

