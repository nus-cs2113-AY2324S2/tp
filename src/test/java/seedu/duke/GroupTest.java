package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupTest {
    @Test
    public void groupTest() {
        String expectedName = "GroupName";
        Group group = new Group(expectedName);
        assertTrue(group.name.equals(expectedName), "Group name should be snt to the provided constructor argument.");
    }
}
