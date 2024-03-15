package seedu.duke;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DukeTest {

    @Test
    public void addTest() {
        TravelActivityList travelActivityList = new TravelActivityList();
        travelActivityList.addTask(new TravelActivity("visit museum"));
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
    }

    @Test
    public void deleteTest() {
        //add the plan
        TravelActivityList travelActivityList = new TravelActivityList();
        travelActivityList.addTask(new TravelActivity("visit museum"));
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //delete the plan
        travelActivityList.removeTask(1);
        assertEquals("cant be found", travelActivityList.getDescription("visit museum"));
    }

    @Test
    public void getNoTasksTest() {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        travelActivityList.addTask(new TravelActivity("visit museum"));
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //add the second and third plan
        travelActivityList.addTask(new TravelActivity("go to beach"));
        assertEquals("go to beach", travelActivityList.getDescription("go to beach"));
        travelActivityList.addTask(new TravelActivity("shopping"));
        assertEquals("shopping", travelActivityList.getDescription("shopping"));
        //check number of tasks
        assertEquals(3, travelActivityList.getNoOfTasks());
        //delete the first plan
        travelActivityList.removeTask(1);
        assertEquals("cant be found", travelActivityList.getDescription("visit museum"));
        //check number of tasks
        assertEquals(2, travelActivityList.getNoOfTasks());



    }

    @Test
    public void trueTest(){
        assertTrue(true);
    }
    @Test
    public void testStringCoversion(){
        assertEquals("visit museum", new TravelActivity("visit museum").toString());
    }

}
