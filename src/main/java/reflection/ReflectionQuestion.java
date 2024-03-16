package reflection;

public class ReflectionQuestion {
    private final String QUESTION;

    public ReflectionQuestion(String question) {
        this.QUESTION = question;
    }

    @Override
    public String toString() {
        return QUESTION;
    }
}
