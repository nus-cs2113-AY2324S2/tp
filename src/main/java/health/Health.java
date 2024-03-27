package health;

import java.time.LocalDate;

/**
 * Represents a Health object to track user's health information.
 */
public class Health {
    protected LocalDate date = null;
    public Health() {
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString(){
        return getDate().toString();
    }
}
