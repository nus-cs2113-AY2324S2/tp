package byteceps.activities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ActivityTest {

    @Test
    public void getActivityName_validName_setNewName() {
        String activityName = "Running";
        Activity activity = new Activity(activityName);
        assertEquals(activityName, activity.getActivityName());
    }

    @Test
    public void getActivityName_nullName_setNull() {
        String activityName = null;
        Activity activity = new Activity(activityName);
        assertNull(activity.getActivityName());
    }

    @Test
    public void getActivityName_emptyName_setEmptyName() {
        String activityName = "";
        Activity activity = new Activity(activityName);
        assertEquals(activityName, activity.getActivityName());
    }

}
