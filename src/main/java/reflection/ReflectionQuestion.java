package reflection;

/**
 * Represents a reflection question.
 */
public class ReflectionQuestion {
    private String question;

    /**
     * Constructs a ReflectionQuestion with the given question text.
     *
     * @param question The text of the reflection question.
     */
    public ReflectionQuestion(String question) {
        this.question = question;
    }

    /**
     * Retrieves the text of the reflection question.
     *
     * @return The text of the reflection question.
     */
    @Override
    public String toString() {
        return question;
    }
}
