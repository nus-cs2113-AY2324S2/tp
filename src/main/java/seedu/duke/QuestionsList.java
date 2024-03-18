package seedu.duke;

import java.util.ArrayList;

public class QuestionsList {
    private ArrayList<Question> questionsList;


    public QuestionsList() {
        questionsList = new ArrayList<>();

    }

    public void addQuestion(Question question){
        questionsList.add(question);
    }

    public void getAllExplanations(){
        for (Question question: questionsList) {
            // UI stuff
            int questionNum = questionsList.indexOf(question) + 1; // + 1 coz zero index
            String header = "Explanation for question " + questionNum + ":" + System.lineSeparator();

            System.out.println(header + question.getExplanation());
        }
    }
}

