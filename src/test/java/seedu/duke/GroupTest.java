package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Optional<Group> group = Group.getOrCreateGroup(expectedName);
        assertEquals(expectedName, group.get().getGroupName(), "Group name is not the same as expected");
    }

    @Test
    public void testAddUserToGroup() {
        String groupName = "TestGroup";
        Optional<Group> group = Group.getOrCreateGroup(groupName);
        if (group.isEmpty()) {
            System.out.println("Group does not exist.");
            return;
        }

        User user = group.get().addMember("TestUser");

        assertTrue(group.get().getMembers().contains(user), "User was not added to the group");
    }

    @Test
    public void testGetOrCreateGroup() {
        String groupName = "NewGroup";
        Optional<Group> newGroup = Group.getOrCreateGroup(groupName);

        assertEquals(groupName, newGroup.get().getGroupName(), "Group name is not the expected value");

        Group.exitGroup();
        Optional<Group> existingGroup = Group.getOrCreateGroup(groupName);

        assertEquals(newGroup.get(), existingGroup.get(), "getOrCreateGroup should return the existing group");
        assertTrue(Group.getCurrentGroup().isEmpty(), "Current group should be empty after exiting");
    }

    @Test
    public void testExitGroup() {
        String groupName = "ExitingGroup";
        Group.getOrCreateGroup(groupName);
        Group.exitGroup();
        assertTrue(Group.getCurrentGroup().isEmpty(), "Did not successfully exit the group");
    }

    @Test
    public void testGetCurrentGroup() {
        String groupName = "CurrentGroup";
        Optional<Group> group = Group.getOrCreateGroup(groupName);

        assertEquals(group.get(), Group.getCurrentGroup().get(), "Current group is not the expected group");

        Group.exitGroup();
        assertTrue(Group.getCurrentGroup().isEmpty(), "Current group should be empty after exiting");
    }
}
