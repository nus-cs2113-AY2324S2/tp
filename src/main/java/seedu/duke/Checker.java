package seedu.duke;
import seedu.duke.Problem;

import java.util.ArrayList;

public class Checker {
    private Double[] userAnswer;
    private ArrayList<Problem> test;
    private Boolean[] isCorrect;
    private int correctNumber;
    double accuracy;

    Boolean checkCorrectness(Problem problem, double answer){
        if(Math.abs(problem.getAnser()-answer)<0.01){
            return ture;
        }
        return false;
    }

    public Checker(Double[] userAnswer, ArrayList<Problem> test){
        assert userAnswer != null: "Input null userAnswer!";
        assert test != null: "Input null test!";
        this.userAnswer = userAnswer;
        this.test = test;
        this.isCorrect = new Boolean[test.size()];
        this.correctNumber = 0;
        for(int i=0;i<test.size();i++){
            isCorrect[i] = checkCorrectness(test.get(i), userAnswer[i]);
            correctNumber++;
        }
        accuracy = (double) correctNumber /test.size();

    }

    public Boolean[] checkAnswer(){
        return isCorrect;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public double getAccuracy(){return accuracy;}
}