package newsonthego;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatDate {

    private static  final String[] inputDateFormats = {
        "MM dd yyyy", // 01 02 2024 -> January 2, 2024
        "MMMM dd yyyy" // January 02 2024 -> January 2, 2024
    };

    private static final SimpleDateFormat FILE_FORMAT = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);

    public static String formatFromUser(String date) {
        String result;
        for (String dateFormat : inputDateFormats) {
            SimpleDateFormat targetFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
            try {
                Date formattedDate = targetFormat.parse(date);
                result = FILE_FORMAT.format(formattedDate);
                return result;
            } catch (ParseException ignore) {
                // Try another format
            }
        }
        return null;
    }
}
