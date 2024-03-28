package seedu.duke;

import java.util.ArrayList;

public class AnswerTracker {
    protected ArrayList<ArrayList<String>> userAnswers;
    protected ArrayList<ArrayList<Boolean>> isCorrect;

    public AnswerTracker() {
        userAnswers = new ArrayList<>();
        isCorrect = new ArrayList<>();
    }

    public String getUserAnswers(int index, int attemptNumber) {
        return userAnswers.get(attemptNumber).get(index);
    }

    public Boolean getIsCorrect(int index, int attemptNumber) {
        return isCorrect.get(attemptNumber).get(index);
    }

    public void addUserAnswers(ArrayList<String> answers) {
        userAnswers.add(answers);
    }

    public void addUserCorrectness(ArrayList<Boolean> correctness) {
        isCorrect.add(correctness);
    }
}
