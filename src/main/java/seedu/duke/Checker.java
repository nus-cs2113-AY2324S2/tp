package seedu.duke;
import seedu.duke.Problem;

import java.util.ArrayList;

public class Checker {
    private Double[] userAnswer;
    private ArrayList<Problem> test;
    private Boolean[] isCorrect;
    private int correctNumber;

    public Checker(Double[] userAnswer, ArrayList<Problem> test){
        this.userAnswer = userAnswer;
        this.test = test;
        this.isCorrect = new Boolean[test.size()];
        this.correctNumber = 0;
        for(int i=0;i<test.size();i++){
            isCorrect[i] = test.get(i).getAnswer() == userAnswer[i];
            correctNumber++;
        }
    }

    public Boolean[] checkAnswer(){
        return isCorrect;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }
}