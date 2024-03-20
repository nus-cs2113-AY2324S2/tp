package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupTest {
    @Test
    public void groupTest() {
        String expectedName = "GroupName";
        Group group = new Group(expectedName);
        assertTrue(group.groupName.equals(expectedName), "Group name is not the same as expected");
    }
}
