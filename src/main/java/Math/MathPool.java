package Math;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;


public class MathPool {
    private ArrayList<MathQuestion> poolOfQuestions;
    private final Random random;


    public MathPool() {
        poolOfQuestions = new ArrayList<MathQuestion>();
        random = new Random();
    }

    public void addMathQuestion(String wordProblem, int solution, int difficulty) {
        MathQuestion problem = new MathQuestion(wordProblem, solution, difficulty);
        poolOfQuestions.add(problem);
    }

    /*public MathQuestion getQuestionByDifficulty(int targetDifficulty) {
        ArrayList<MathQuestion> filteredQuestions = new ArrayList<>();
        for (MathQuestion question : poolOfQuestions) {
            if (question.getDifficulty() == targetDifficulty) {
                filteredQuestions.add(question);
            }
        }
        if (!filteredQuestions.isEmpty()) {
            int index = random.nextInt(filteredQuestions.size());
            return filteredQuestions.get(index);
        } else {
            return null;
        }
    }*/

    public MathQuestion getQuestionByDifficulty(int targetDifficulty) {
        ArrayList<MathQuestion> filteredQuestions = new ArrayList<>();
        for (MathQuestion question : poolOfQuestions) {
            if (question.getDifficulty() == targetDifficulty) {
                filteredQuestions.add(question);
            }
        }
        if (!filteredQuestions.isEmpty()) {
            // Shuffle the list of questions
            Collections.shuffle(filteredQuestions);
            // Iterate through shuffled questions
            for (MathQuestion question : filteredQuestions) {
                // Return the first question found (after shuffling)
                return question;
            }
        }
        return null; // Return null if no questions of the specified difficulty are found
    }


    public void init() {
        // Difficulty 0
        addMathQuestion("1 + 1 = ", 2, 0);
        addMathQuestion("1 + 2 = ", 3, 0);
        addMathQuestion("3 + 1 = ", 4, 0);
        addMathQuestion("2 + 5 = ", 7, 0);
        addMathQuestion("10 + 23 = ", 33, 0);

        // Difficulty 1
        addMathQuestion("5 - 3 = ", 2, 1);
        addMathQuestion("8 + 4 = ", 12, 1);
        addMathQuestion("10 - 6 = ", 4, 1);
        addMathQuestion("7 + 6 = ", 13, 1);
        addMathQuestion("9 - 2 = ", 7, 1);

        // Difficulty 2
        addMathQuestion("6 * 4 = ", 24, 2);
        addMathQuestion("12 / 3 = ", 4, 2);
        addMathQuestion("9 * 5 = ", 45, 2);
        addMathQuestion("20 / 4 = ", 5, 2);
        addMathQuestion("8 * 7 = ", 56, 2);

        // Difficulty 3
        addMathQuestion("5^2 = ", 25, 3);
        addMathQuestion("square root of 144 = ", 12, 3);
        addMathQuestion("3^3 = ", 27, 3);
        addMathQuestion("square root of 81 = ", 9, 3);
        addMathQuestion("7^2 = ", 49, 3);

        // Difficulty 4
        addMathQuestion("What is the sum of all angles in a triangle?", 180, 4);
        addMathQuestion("How many sides does a hexagon have?", 6, 4);
        addMathQuestion("What is the area of a square with side length 5?", 25, 4);
        addMathQuestion("What is the perimeter of a rectangle with sides 4 and 6?", 20, 4);
    }

}
