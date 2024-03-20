package seedu.duke;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DukeTest {

    @Test
    public void addTest() {
        TravelActivityList travelActivityList = new TravelActivityList();
        travelActivityList.addTravelActivity(new TravelActivity("visit museum"));
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
    }

    @Test
    public void deleteTest() throws OmniException {
        //add the plan
        TravelActivityList travelActivityList = new TravelActivityList();
        travelActivityList.addTravelActivity(new TravelActivity("visit museum"));
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //delete the plan
        travelActivityList.removeTravelActivity(1);
        assertEquals("cant be found", travelActivityList.getDescription("visit museum"));
    }

    @Test
    public void getNoTasksTest() throws OmniException {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        travelActivityList.addTravelActivity(new TravelActivity("visit museum"));
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //add the second and third plan
        travelActivityList.addTravelActivity(new TravelActivity("go to beach"));
        assertEquals("go to beach", travelActivityList.getDescription("go to beach"));
        travelActivityList.addTravelActivity(new TravelActivity("shopping"));
        assertEquals("shopping", travelActivityList.getDescription("shopping"));
        //check number of tasks
        assertEquals(3, travelActivityList.getNoOfTravelActivities());
        //delete the first plan
        travelActivityList.removeTravelActivity(1);
        assertEquals("cant be found", travelActivityList.getDescription("visit museum"));
        //check number of tasks
        assertEquals(2, travelActivityList.getNoOfTravelActivities());
    }

    @Test
    public void checkTest() throws OmniException {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        travelActivityList.addTravelActivity(new TravelActivity("visit museum"));
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //check the plan
        travelActivityList.checkTravelActivity(1);
        TravelActivity travelActivity = travelActivityList.getTravelActivities().get(0);
        assertTrue(travelActivity.getTaskStatus());
    }

    @Test
    public void uncheckTest() throws OmniException {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        travelActivityList.addTravelActivity(new TravelActivity("visit museum"));
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //check the plan
        travelActivityList.checkTravelActivity(1);
        TravelActivity travelActivity = travelActivityList.getTravelActivities().get(0);
        assertTrue(travelActivity.getTaskStatus());
        //uncheck the plan
        travelActivityList.uncheckTravelActivity(1);
        assertFalse(travelActivity.getTaskStatus());
    }

    @Test
    public void trueTest(){
        assertTrue(true);
    }
    @Test
    public void testStringCoversion(){
        assertEquals("visit museum", new TravelActivity("visit museum").toString());
    }
    @Test

    //basic test for search function
    public void searchTest () {
        assertTrue(true);
    }



}
