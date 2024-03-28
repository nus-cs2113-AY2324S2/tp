package seedu.duke;

import seedu.duke.exceptions.CustomException;

import java.util.ArrayList;

public class QuestionsList {
    private ArrayList<Question> chosenQuestionsList;

    public QuestionsList() {
        chosenQuestionsList = new ArrayList<>();
    }

    public void addQuestion(Question question){
        chosenQuestionsList.add(question);
    }

    public Question getQuestionUnit(int index){
        return chosenQuestionsList.get(index);
    }

    public int getSize() {
        return chosenQuestionsList.size();
    }

    public String getOneExplanation(int questionNum) {
        assert questionNum > 0 : "questionNum should be more than 0";
        int questionIndex = questionNum - 1; // -1 coz zero index
        Question question = chosenQuestionsList.get(questionIndex);
        return question.getExplanation();
    }
    public String getAllExplanations() throws CustomException {
        if (chosenQuestionsList.isEmpty()) {
            throw new CustomException("No questions yet");
        }
        StringBuilder allExplanations = new StringBuilder();

        for (Question question: chosenQuestionsList) {
            int questionNum = chosenQuestionsList.indexOf(question) + 1; // +1 coz zero index
            String header = "Explanation for question " + questionNum + ":" + System.lineSeparator();
            String explanationForOneQuestion = header + question.getExplanation() + System.lineSeparator();

            allExplanations.append(explanationForOneQuestion);
            allExplanations.append(System.lineSeparator());
        }

        return allExplanations.toString();
    }

    public String getOneSolution(int questionNum) {
        assert questionNum > 0 : "questionNum should be more than 0";
        int questionIndex = questionNum - 1; // -1 coz zero index
        Question question = chosenQuestionsList.get(questionIndex);
        return question.getSolution();
    }

    public String getAllSolutions() throws CustomException {
        if (chosenQuestionsList.isEmpty()) {
            throw new CustomException("No questions yet");
        }
        StringBuilder allSolutions = new StringBuilder();

        for (Question question: chosenQuestionsList) {
            int questionNum = chosenQuestionsList.indexOf(question) + 1; // +1 coz zero index
            String header = "Solution for question " + questionNum + ":" + System.lineSeparator();
            String solutionForOneQuestion = header + question.getSolution() + System.lineSeparator();

            allSolutions.append(solutionForOneQuestion);
            allSolutions.append(System.lineSeparator());
        }

        return allSolutions.toString();
    }
}
