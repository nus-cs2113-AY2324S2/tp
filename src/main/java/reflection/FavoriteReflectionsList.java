package reflection;

import java.util.ArrayList;

/**
 * Represents a list of favorite reflection questions.
 */
public class FavoriteReflectionsList {
    private ArrayList<ReflectionQuestion> favouritesList;

    /**
     * Constructs a FavoriteReflectionsList with an empty list of favorites.
     */
    public FavoriteReflectionsList() {
        this.favouritesList = new ArrayList<>();
    }

    /**
     * Adds a reflection question to the list of favorites.
     *
     * @param reflectionQuestion The reflection question to be added.
     */
    public void addReflectionQuestion(ReflectionQuestion reflectionQuestion) {
        if (!reflectionQuestion.toString().isBlank()) {
            favouritesList.add(reflectionQuestion);
        }
    }

    /**
     * Retrieves the list of favorite reflection questions.
     *
     * @return The list of favorite reflection questions.
     */
    public ArrayList<ReflectionQuestion> getFavouritesList() {
        return favouritesList;
    }


    /**
     * Retrieves a favorite reflection question by its index in the list.
     *
     * @param favouritesId The index of the favorite reflection question to retrieve.
     * @return The reflection question at the specified index.
     */
    public ReflectionQuestion get(int favouritesId) {
        return favouritesList.get(favouritesId);
    }
}
