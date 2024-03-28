package seedu.duke;

import java.util.ArrayList;

public class FavouritesList {
    private ArrayList<Favourites> favourites;

    public FavouritesList() {
        this.favourites = new ArrayList<>();
    }

    public FavouritesList(ArrayList<Favourites> favourites) {
        this.favourites = favourites;
    }

    public void addFavourite(Favourites favourite) {
        favourites.add(favourite);
    }

    public void deleteFavourite(int index) {
        if (index >= 0 && index < favourites.size()) {
            favourites.remove(index);
        }
    }

    public ArrayList<Favourites> getFavourites() {
        return favourites;
    }


    public boolean isEmpty() {
        return favourites.isEmpty();
    }

    public String getFormattedFavourites() {
        if(favourites.isEmpty()) {
            return "A blank canvas! Let's fill it with some love~";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < favourites.size(); i++) {
                sb.append((i + 1)).append(". ").append(favourites.get(i).toString()).append("\n");
            }
            return sb.toString().trim();
        }
    }

    public ArrayList<Favourites> findFavourites(String keyword) {
        ArrayList<Favourites> matchingSaves = new ArrayList<>();
        for(Favourites favourite: favourites) {
            if(favourite.getDescription().contains(keyword)) {
                matchingSaves.add(favourite);
            }
        }
        return matchingSaves;
    }
}
