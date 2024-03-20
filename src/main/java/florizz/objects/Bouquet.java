package florizz.objects;

import java.util.Locale;
import java.util.Objects;

public class Bouquet {
    private final String bouquetName;
    public Bouquet(String bouquetName){
        this.bouquetName = bouquetName;
    }
    @Override
    public String toString() {
        return bouquetName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Bouquet)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Bouquet c = (Bouquet) obj;

        // Compare the data members and return accordingly
        return Objects.equals(c.bouquetName.toUpperCase(), this.bouquetName.toUpperCase());
    }
}
