package reflection;

import ui.Ui;

import java.util.ArrayList;

public class Reflection {
    private ArrayList<ReflectionQuestion> fiveRandomQuestions;
    private ReflectionQuestionBank questionBank;

    public Reflection() {
        this.questionBank = new ReflectionQuestionBank();
    }

    public void printFiveRandomQuestions() {
        fiveRandomQuestions = questionBank.getFiveRandomQuestions();
        Ui.printList(fiveRandomQuestions);
    }


    public ReflectionQuestionBank getQuestionBank() {
        return questionBank;
    }

}
