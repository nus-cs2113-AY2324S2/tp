package brokeculator.frontend;
import static org.junit.jupiter.api.Assertions.assertEquals;

import brokeculator.frontend.UI;
import org.junit.jupiter.api.Test;
public class UITest {
    @Test
    public void prettify_string_expectStringEnclosedWithThreeAsterisks() {
        String inputString = "Hello";
        String decorator = "***";
        String expectedString = "***" + System.lineSeparator()
                                + "Hello"  + System.lineSeparator() + "***";
        String decoratedString = UI.prettify(inputString, decorator, decorator);
        assertEquals(expectedString, decoratedString);
    }
}
