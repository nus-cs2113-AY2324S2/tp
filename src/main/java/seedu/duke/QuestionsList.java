package seedu.duke;

import seedu.duke.exceptions.CustomException;

import java.util.ArrayList;

public class QuestionsList {
    private ArrayList<Question> questionsList;


    public QuestionsList() {
        questionsList = new ArrayList<>();

    }

    public void addQuestion(Question question){
        questionsList.add(question);
    }

    public int getSize() {
        return questionsList.size();
    }

    public String getAllExplanations() throws CustomException {
        if (questionsList.isEmpty()) {
            throw new CustomException("No questions yet");
        }
        StringBuilder allExplanations = new StringBuilder();

        for (Question question: questionsList) {
            int questionNum = questionsList.indexOf(question) + 1; // + 1 coz zero index
            String header = "Explanation for question " + questionNum + ":" + System.lineSeparator();
            String explanationForOneQuestion = header + question.getExplanation() + System.lineSeparator();

            allExplanations.append(explanationForOneQuestion);
            allExplanations.append(System.lineSeparator());
        }

        return allExplanations.toString();
    }
}

