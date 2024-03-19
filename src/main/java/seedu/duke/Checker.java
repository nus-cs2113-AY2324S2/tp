package seedu.duke;
import java.util.Scanner;
import seedu.duke.Problem;

import java.util.ArrayList;

public class Checker {
    private Double[] userAnswer;
    private final Test test;
    private Boolean[] isCorrect;
    private int correctNumber;
    double accuracy;
    long time;

    Boolean checkCorrectness(Problem problem, double answer){
        return Math.abs(problem.getAnswer() - answer) < 0.01;
    }

    public Checker(Test test){
        assert test != null: "Input null test!";
        this.userAnswer = new Double[test.getNumber()];
        this.test = test;
        this.isCorrect = new Boolean[test.getNumber()];
        this.correctNumber = 0;
        accuracy = 0.0;
        this.time = 0;

    }
    void getUserAnswer(){
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<test.getNumber();i++){
            Problem problem = test.getProblem().get(i);
            System.out.println(problem.unsolved());
            String userInput = scanner.nextLine();
            double answer = Double.NEGATIVE_INFINITY;
            try {
                answer = Double.parseDouble(userInput);
            }catch (NumberFormatException e){
                System.out.println("Invalid Input, please enter a number");
                continue;
            }
            userAnswer[i] = answer;
            if(checkCorrectness(problem, answer)){
                correctNumber++;
                isCorrect[i] = true;
            }

        }

        long endTime = System.currentTimeMillis();
        accuracy = (double) correctNumber /test.getNumber();
        this.time = (endTime - startTime)/1000;
    }

    public Boolean[] checkAnswer(){
        return isCorrect;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public double getAccuracy(){return accuracy;}

    public long getTime(){return time;}
}