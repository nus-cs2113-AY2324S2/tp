package seedu.brokeculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class UITest {
    @Test
    public void prettify_string_expectDecoratedString() {
        String inputString = "Hello";
        String decorator = "***";
        String expectedString = "***" + System.lineSeparator()
                                + "Hello"  + System.lineSeparator() + "***";
        String decoratedString = UI.prettify(inputString, decorator, decorator);
        assertEquals(expectedString, decoratedString);
    }
}
