package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GroupTest {
    @BeforeEach
    public void setup() {
        // Reset the state before each test
        Group.exitGroup();
    }

    @AfterEach
    public void teardown() {
        // Clean up after each test
        Group.exitGroup();
    }

    @Test
    public void testGroupCreation() {
        String expectedName = "GroupName";
        Group group = Group.getOrCreateGroup(expectedName);
        assertEquals(expectedName, group.getGroupName(), "Group name is not the same as expected");
    }

    @Test
    public void testAddUserToGroup() {
        String groupName = "TestGroup";
        Group group = Group.getOrCreateGroup(groupName);
        User user = group.addMember("TestUser");

        assertTrue(group.getMembers().contains(user), "User was not added to the group");
    }

    @Test
    public void testGetOrCreateGroup() {
        String groupName = "NewGroup";
        Group newGroup = Group.getOrCreateGroup(groupName);

        assertEquals(groupName, newGroup.getGroupName(), "Group name is not the expected value");

        Group.exitGroup();
        Group existingGroup = Group.getOrCreateGroup(groupName);

        assertEquals(newGroup, existingGroup, "getOrCreateGroup should return the existing group");
    }

    @Test
    public void testExitGroup() {
        String groupName = "ExitingGroup";
        Group.getOrCreateGroup(groupName);
        Group.exitGroup();
        Assertions.assertTrue(Group.currentGroupName.isEmpty(), "Did not successfully exit the group");
    }
}
