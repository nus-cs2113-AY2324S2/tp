package seedu.voyagers;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CurrencyTest {
    List<Currency> items = List.of(Currency.USD, Currency.EUR, Currency.GBP, Currency.JPY, Currency.AUD,
            Currency.CAD, Currency.CHF, Currency.CNY, Currency.SEK, Currency.NZD, Currency.MXN, Currency.SGD,
            Currency.HKD, Currency.NOK, Currency.KRW, Currency.TRY, Currency.RUB, Currency.INR, Currency.BRL,
            Currency.ZAR);
    Random random = new Random();
    @Test
    public void hasSymbol() {

        int randomNumber = random.nextInt(19);
        Currency randomItem = items.get(randomNumber);

        assertNotNull(randomItem.getSymbol());
        assertTrue(randomItem.getSymbol() instanceof String);

    }

    @Test
    public void hasName() {
        int randomNumber = random.nextInt(19);
        Currency randomItem = items.get(randomNumber);
        assertNotNull(randomItem.getName());
        assertTrue(randomItem.getName() instanceof String);
    }
}
