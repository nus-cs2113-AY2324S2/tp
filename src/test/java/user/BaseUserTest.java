package user;

import customexceptions.SecurityException;
import org.junit.jupiter.api.Test;

import userinterface.UI;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseUserTest {
    @Test
    public void sampleTest() throws SecurityException {
        UI ui = new UI();
        BaseUser user = new BaseUser("Bob", ui);
        Authentication auth = user.getAuthentication();
        assertTrue(auth.checkPassword("Bob", "password"));
    }
}
