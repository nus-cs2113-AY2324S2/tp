package Math;
import java.util.Random;
import java.util.ArrayList;

public class MathPool {
    private ArrayList<MathQuestion> poolOfQuestions;
    private final Random random;


    public MathPool(){
        random = new Random();
    }

    public void addMathQuestion(String wordProblem, int solution, int difficulty){
        MathQuestion problem = new MathQuestion(wordProblem, solution, difficulty);
        poolOfQuestions.add(problem);
    }

    public MathQuestion getQuestionByDifficulty(int targetDifficulty){
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
    }

}
