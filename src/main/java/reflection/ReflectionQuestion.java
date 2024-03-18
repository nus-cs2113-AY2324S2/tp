package reflection;

public class ReflectionQuestion {
    private String question;

    public ReflectionQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return question;
    }
}
