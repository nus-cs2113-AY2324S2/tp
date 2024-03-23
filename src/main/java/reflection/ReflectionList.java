package reflection;

import java.util.ArrayList;

/**
 * Represents a list of reflection questions.
 */
public class ReflectionList {
    protected ArrayList<ReflectionQuestion> reflectionList;

    /**
     * Constructs a ReflectionList with an empty list of reflections.
     */
    public ReflectionList() {
        this.reflectionList = new ArrayList<>();
    }

    /**
     * Adds a reflection question to the list.
     *
     * @param reflectionQuestion The reflection question to be added.
     */
    public void addReflectionQuestion(ReflectionQuestion reflectionQuestion) {
        if (!reflectionQuestion.toString().isBlank()) {
            reflectionList.add(reflectionQuestion);
        }
    }

    /**
     * Retrieves the size of the reflection list.
     *
     * @return The size of the reflection list.
     */
    public int getSize() {
        return reflectionList.size();
    }

    /**
     * Retrieves the list of reflection questions.
     *
     * @return The list of reflection questions.
     */
    public ArrayList<ReflectionQuestion> getReflectionList() {
        return reflectionList;
    }
}

