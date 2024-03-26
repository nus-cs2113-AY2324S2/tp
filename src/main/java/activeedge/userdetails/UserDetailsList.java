package activeedge.userdetails;

import java.util.ArrayList;

public class UserDetailsList {
    public static final ArrayList<UserDetails> DETAILS_LIST = new ArrayList<UserDetails>();

    public void add(UserDetails details) {
        DETAILS_LIST.add(details);
    }

    public static UserDetails get() {
        int index = 0;
        if (index >= 0 && index < DETAILS_LIST.size()) {
            return DETAILS_LIST.get(index);
        } else {
            // Handle index out of bounds, you can throw an exception or return null
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }
}
