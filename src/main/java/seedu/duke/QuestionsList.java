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

    // user enters "explain 1" to get explanation for question1
    public String getOneExplanation(int questionNum) {
        int questionIndex = questionNum - 1; // -1 coz zero index
        Question question = questionsList.get(questionIndex);
        return question.getExplanation();
    }

    // user enters "solution 1" to get solution for question1
    public String getOneSolution(int questionNum) {
        int questionIndex = questionNum - 1; // -1 coz zero index
        Question question = questionsList.get(questionIndex);
        return question.getSolution();
    }

    // user enters "solution -all" to get ALL solutions
    public String getAllSolutions() throws CustomException {
        if (questionsList.isEmpty()) {
            throw new CustomException("No questions yet");
        }
        StringBuilder allQuestions = new StringBuilder();

        for (Question question: questionsList) {
            int questionNum = questionsList.indexOf(question) + 1; // +1 coz zero index
            String header = "Solution for question " + questionNum + ":" + System.lineSeparator();
            String solutionForOneQuestion = header + question.getSolution() + System.lineSeparator();

            allQuestions.append(solutionForOneQuestion);
            allQuestions.append(System.lineSeparator());
        }

        return allQuestions.toString();
    }

    public String getAllQuestions() throws CustomException {
        if (questionsList.isEmpty()) {
            throw new CustomException("No questions yet");
        }
        StringBuilder allQuestions = new StringBuilder();

        for (Question question : questionsList) {
            int questionNum = questionsList.indexOf(question) + 1; // +1 coz zero index
            String header = "Question " + questionNum + ":" + System.lineSeparator();
            String displayQuestion = header + question.getQuestion() + System.lineSeparator();

            allQuestions.append(displayQuestion);
            allQuestions.append(System.lineSeparator());
        }

        return allQuestions.toString();
    }
}
