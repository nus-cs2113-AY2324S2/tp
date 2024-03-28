package activeedge.userdetails;

import java.util.ArrayList;

/**
 * The UserDetailsList class represents a list of user details.
 * It provides methods to add user details, retrieve user details, and clear the list.
 */
public class UserDetailsList {

    /**
     * The list containing user details.
     */
    public static final ArrayList<UserDetails> detailsList = new ArrayList<>();

    /**
     * Adds user details to the list.
     *
     * @param details The user details to add.
     */
    public void add(UserDetails details) {
        detailsList.add(details);
    }

    /**
     * Retrieves user details from the list.
     *
     * @return The user details retrieved.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public static UserDetails get() {
        int index = 0;
        if (index >= 0 && index < detailsList.size()) {
            return detailsList.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    /**
     * Clears the list of user details.
     */
    public static void clearDetailsList() {
        detailsList.clear();
    }
}

