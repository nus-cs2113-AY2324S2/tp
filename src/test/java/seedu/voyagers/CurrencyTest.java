package seedu.voyagers;

import org.junit.Test;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CurrencyTest {

    @Test
    public void hasSymbol() {
        Random random = new Random();
        int randomNumber = random.nextInt(19);

        List<Currency> items = List.of(Currency.USD, Currency.EUR, Currency.GBP, Currency.JPY, Currency.AUD,
                Currency.CAD, Currency.CHF, Currency.CNY, Currency.SEK, Currency.NZD, Currency.MXN, Currency.SGD,
                Currency.HKD, Currency.NOK, Currency.KRW, Currency.TRY, Currency.RUB, Currency.INR, Currency.BRL,
                Currency.ZAR);

        Currency randomItem = items.get(randomNumber);
        assertNotNull(randomItem.getName());
        assertNotNull(randomItem.getSymbol());
        assertTrue(randomItem.getSymbol() instanceof String);
        assertTrue(randomItem.getName() instanceof String);


    }
}
