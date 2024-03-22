package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupTest {
    @Test
    public void groupCreationTest() {
        String expectedName = "GroupName";
        Group group = new Group(expectedName);
        assertEquals(expectedName, group.getGroupName(), "Group name is not the same as expected");
    }
    @Test
    public void addUserToGroupTest() {
        String groupName = "TestGroup";
        Group group = new Group(groupName);
        User user = new User("TestUser");

        group.addUsers(user);

        assertTrue(group.getUsers().contains(user), "User was not added to the group");
    }
    @Test
    public void getOrCreateGroupTest() {
        String groupName = "NewGroup";
        Group.getOrCreateGroup(groupName);

        assertTrue(Group.groups.containsKey(groupName), "New group was not created");
    }
    @Test
    public void exitGroupTest() {
        String groupName = "ExitingGroup";
        Group.getOrCreateGroup(groupName);
        Group.exitGroup();
        Assertions.assertNull(Group.currentGroupName, "Did not successfully exit the group");
    }
}
