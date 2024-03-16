package reflection;

import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class Reflection {
    private ArrayList<ReflectionQuestion> fiveRandomQuestions;
    private ReflectionQuestionBank questionBank;
    private static final String QUESTION_BANK_FILE_PATH = "data/question_bank.txt";

    public Reflection() {
        this.questionBank = new ReflectionQuestionBank();
        ArrayList<String> questionsList = Storage.loadDataFromFile(QUESTION_BANK_FILE_PATH);
        for (String ques : questionsList) {
            ReflectionQuestion reflectionQuestion = new ReflectionQuestion(ques);
            this.questionBank.addReflectionQuestion(reflectionQuestion);
        }
    }

    public void printFiveRandomQuestions() {
        fiveRandomQuestions = questionBank.getFiveRandomQuestions();
        Ui.printList(fiveRandomQuestions);
    }


    public ReflectionQuestionBank getQuestionBank() {
        return questionBank;
    }

}
