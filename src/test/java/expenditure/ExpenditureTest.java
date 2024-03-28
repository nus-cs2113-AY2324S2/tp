package expenditure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenditureTest {

    @Test
    public void testExpenditureConstructor() {

        String description = "food";
        Float amount = 50.0f;
        String date = "01.01.2024";

        Expenditure expenditure = new Expenditure(description, amount, date);

        assertEquals(description, expenditure.getDescription());
        assertEquals(amount, expenditure.getAmount());
        assertEquals(date, expenditure.getDate());
    }

    @Test
    public void getDescription() {
        String description = "food";
        Float amount = 50.0f;
        String date = "01.01.2024";

        Expenditure expenditure = new Expenditure(description, amount, date);

        String storedDescription = expenditure.getDescription();
        assertEquals(description,storedDescription);
    }

    @Test
    public void getAmount() {
        String description = "food";
        Float amount = 50.0f;
        String date = "01.01.2024";

        Expenditure expenditure = new Expenditure(description, amount, date);

        float storedAmount = expenditure.getAmount();;
        assertEquals(amount, storedAmount);
    }

    @Test
    public void getDate() {
        String description = "food";
        Float amount = 50.0f;
        String date = "01.01.2024";

        Expenditure expenditure = new Expenditure(description, amount, date);

        String storedDate = expenditure.getDate();;
        assertEquals(date, storedDate);
    }

    @Test
    public void testToString() {
        String description = "food";
        Float amount = 50.0f;
        String date = "01.01.2024";

        Expenditure expenditure = new Expenditure(description, amount, date);

        String expectedToString = "food | Cost: $50.0 | date: 01.01.2024";
        assertEquals(expectedToString, expenditure.toString());
    }

    @Test
     public void toStringStorage() {
        String description = "food";
        Float amount = 50.0f;
        String date = "01.01.2024";

        Expenditure expenditure = new Expenditure(description, amount, date);

        String expectedToStringStorage = "food | 50.0 | 01.01.2024";

        assertEquals(expectedToStringStorage, expenditure.toStringStorage());
    }
}
