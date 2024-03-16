package reflection;

import exceptions.ReflectException;
import ui.Ui;

import java.util.ArrayList;

public class ReflectionManager {
    private ArrayList<ReflectionQuestion> fiveRandomQuestions;
    private ReflectionQuestionBank questionBank;

    public ReflectionManager() {
        this.questionBank = new ReflectionQuestionBank();
    }

    public void printFiveRandomQuestions() {
        try {
            fiveRandomQuestions = questionBank.getFiveRandomQuestions();
            Ui.printList(fiveRandomQuestions);
        } catch (ReflectException e) {
            Ui.printMessageWithSepNewLine(e.getMessage());
        }

    }


    public ReflectionQuestionBank getQuestionBank() {
        return questionBank;
    }

}
