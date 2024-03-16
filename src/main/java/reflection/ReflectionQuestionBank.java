package reflection;

import java.util.Collections;
import java.util.ArrayList;

public class ReflectionQuestionBank {
    private ArrayList<ReflectionQuestion> reflectionQuestionList;


    public ReflectionQuestionBank() {
        this.reflectionQuestionList = new ArrayList<>();
    }

    public void addReflectionQuestion(ReflectionQuestion question) {
        if (!question.toString().isBlank()) {
            reflectionQuestionList.add(question);
        }
    }

    public ArrayList<ReflectionQuestion> getFiveRandomQuestions() {
        ArrayList<ReflectionQuestion> randomQuestions = new ArrayList<>();

        // Create a copy of the original list
        ArrayList<ReflectionQuestion> copyList = new ArrayList<>(reflectionQuestionList);

        // Shuffle the copy list
        Collections.shuffle(copyList);

        // Select the first five questions from the shuffled copy list and
        // add them to the result list
        for (int i = 0; i < 5; i++) {
            randomQuestions.add(copyList.get(i));
        }

        return randomQuestions;
    }

    public int getTaskListSize() {
        return reflectionQuestionList.size();
    }

    public ArrayList<ReflectionQuestion> getQuestionsList() {
        return reflectionQuestionList;
    }
}
