package user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseUserTest {
    @Test
    public void sampleTest() {
        BaseUser user = new BaseUser("Bob");
        Authentication auth = user.getAuthentication();
        assertTrue(auth.checkPassword("Bob", "password"));
    }
}