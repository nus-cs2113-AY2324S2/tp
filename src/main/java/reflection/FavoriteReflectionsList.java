package reflection;

/**
 * Represents a list of favorite reflection questions.
 */
public class FavoriteReflectionsList extends ReflectionList {

    /**
     * Constructs a FavoriteReflectionsList with an empty list of favorites.
     */
    public FavoriteReflectionsList() {
        super();
    }

    /**
     * Retrieves a favorite reflection question by its index in the list.
     *
     * @param favouritesId The index of the favorite reflection question to retrieve.
     * @return The reflection question at the specified index.
     */
    public ReflectionQuestion get(int favouritesId) {
        return reflectionList.get(favouritesId);
    }
}
