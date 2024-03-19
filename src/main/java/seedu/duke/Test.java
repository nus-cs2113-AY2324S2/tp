package seedu.duke;

import java.util.ArrayList;

public class Test {

    String operators;
    int maxDigits;
    int number;
    ArrayList<Problem> problemList = new ArrayList<>();

    public Test(String operators, int maxDigits, int number) {
        ArrayList<Problem> problemList = new ArrayList<>();

        this.operators = operators;
        this.maxDigits = maxDigits;
        this.number = number;
    }

    public void addToTest(Problem p) {
        problemList.add(p);
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Problem> getProblem() {
        return problemList;
    }

}
