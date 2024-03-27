package Math;

public class MathQuestion {
    private final String question;
    private final int answer;
    private final int difficulty;

    public MathQuestion(String qn, int ans, int diff){
        this.question = qn;
        this.answer = ans;
        this.difficulty = diff;
    }

    public String getQuestion(){
        return question;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean checkAns(int userAns){
        return answer == userAns;
    }
}
