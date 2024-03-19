package time;
import java.time.LocalDate;

public class DateUtils {
    public static LocalDate getStartOfWeek(LocalDate date) {
        return date.minusDays(date.getDayOfWeek().getValue() % 7);
    }
}
