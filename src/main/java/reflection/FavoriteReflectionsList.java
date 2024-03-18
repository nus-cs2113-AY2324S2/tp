package reflection;

import java.util.ArrayList;

public class FavoriteReflectionsList {
    private ArrayList<ReflectionQuestion> favouritesList;

    public FavoriteReflectionsList() {
        this.favouritesList = new ArrayList<>();
    }

    public void addReflectionQuestion(ReflectionQuestion reflectionQuestion) {
        if (!reflectionQuestion.toString().isBlank()) {
            favouritesList.add(reflectionQuestion);
        }
    }

    public ArrayList<ReflectionQuestion> getFavouritesList() {
        return favouritesList;
    }


    public ReflectionQuestion get(int favouritesId) {
        return favouritesList.get(favouritesId);
    }
}
