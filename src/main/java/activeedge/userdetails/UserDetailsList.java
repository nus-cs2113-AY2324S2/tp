package activeedge.userdetails;

import java.util.ArrayList;

public class UserDetailsList {
    public static final ArrayList<UserDetails> detailsList = new ArrayList<UserDetails>();

    public void add(UserDetails details) {
        detailsList.add(details);
    }

    public static UserDetails get() {
        int index = 0;
        if (index >= 0 && index < detailsList.size()) {
            return detailsList.get(index);
        } else {
            // Handle index out of bounds, you can throw an exception or return null
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }
}
