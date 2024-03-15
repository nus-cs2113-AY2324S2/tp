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
    public void trueTest(){
        assertTrue(true);
    }
    @Test
    public void testStringCoversion(){
        assertEquals("visit museum", new TravelActivity("visit museum").toString());
    }

}
