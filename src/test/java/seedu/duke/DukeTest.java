package seedu.duke;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class DukeTest {

    Accommodation accommodationNew1 = new Accommodation("nus rvrc", LocalDate.parse("2007-12-12"),
            "5 years", "campus stay");
    Accommodation accommodationNew2 = new Accommodation("nus pgpr", LocalDate.parse("2017-10-12"),
            "5 years", "campus stay");
    Accommodation accommodationNew3 = new Accommodation("nus utr", LocalDate.parse("2007-09-12"),
            "5 years", "campus stay");
    Landmark landmarkNew1 = new Landmark("berlin wall", LocalDate.parse("2009-12-15"), "5 hours",
            "historic site");
    Landmark landmarkNew2 = new Landmark("utown", LocalDate.parse("2016-08-14"), "10 hours",
            "recreational centre");
    Landmark landmarkNew3 = new Landmark("supper stretch", LocalDate.parse("2021-08-18"), "2 hours",
            "tourist hotspot");
    Food foodNew1 = new Food("utown mala", LocalDate.parse("2019-06-19"), "2 hours",
            "spicy");
    Food foodNew2 = new Food("pgpr mala", LocalDate.parse("2012-07-07"), "1 hours",
            "spicy");
    Food foodNew3 = new Food("pgpr waffle", LocalDate.parse("2006-03-09"), "0.5 hours",
            "non-spicy");
    TravelActivity travelActivityNew1 = new TravelActivity("esplanade", LocalDate.parse("2016-03-19"),
            "3 hours", "concert");
    TravelActivity travelActivityNew2 = new TravelActivity("merlion", LocalDate.parse("2018-04-07"),
            "2 hours", "sightseeing");
    TravelActivity travelActivityNew3 = new TravelActivity("chinatown", LocalDate.parse("2015-02-21"),
            "5 hours", "sightseeing");

    private final PrintStream printedText = System.out;
    private final ByteArrayOutputStream capturedOutputStream = new ByteArrayOutputStream();


    @BeforeEach
    public void setUpPrintLnTest() {
        System.setOut(new PrintStream(capturedOutputStream));
    }

    @AfterEach
    public void restorePrintLn() {
        System.setOut(printedText);
    }

    @Test
    public void addTest() {
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing");
        TravelActivity travelActivity2 = new TravelActivity("visit home",
                LocalDate.parse("2019-12-14"), "5hours", "Sightseeing");
        travelActivityList.addTravelActivity(travelActivity1);
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        travelActivityList.addTravelActivity(travelActivity2);
        assertEquals("visit home", travelActivityList.getDescription("visit home"));
    }

    @Test
    public void deleteTest() throws OmniException {
        //add the plan
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing");
        travelActivityList.addTravelActivity(travelActivity);
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //delete the plan
        travelActivityList.removeTravelActivity(1);
        assertEquals("cant be found", travelActivityList.getDescription("visit museum"));
    }

    @Test
    public void getNoActivitiesTest() throws OmniException {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing");
        TravelActivity travelActivity2 = new TravelActivity("go to beach",
                LocalDate.parse("2018-10-12"),"3hours", "Sightseeing");
        TravelActivity travelActivity3 = new TravelActivity("shopping",
                LocalDate.parse("2020-12-05"),"5hours", "Shopping");
        travelActivityList.addTravelActivity(travelActivity1);
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //add the second and third plan
        travelActivityList.addTravelActivity(travelActivity2);
        assertEquals("go to beach", travelActivityList.getDescription("go to beach"));
        travelActivityList.addTravelActivity(travelActivity3);
        assertEquals("shopping", travelActivityList.getDescription("shopping"));
        //check number of activities
        assertEquals(3, travelActivityList.getNoOfTravelActivities());
        //delete the first plan
        travelActivityList.removeTravelActivity(1);
        assertEquals("cant be found", travelActivityList.getDescription("visit museum"));
        //check number of activities
        assertEquals(2, travelActivityList.getNoOfTravelActivities());
    }

    @Test
    public void checkTest() throws OmniException {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing");
        travelActivityList.addTravelActivity(travelActivity1);
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //check the plan
        travelActivityList.checkTravelActivity(1);
        TravelActivity travelActivity2 = travelActivityList.getTravelActivities().get(0);
        assertTrue(travelActivity2.getActivityStatus());
    }

    @Test
    public void uncheckTest() throws OmniException {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing");
        travelActivityList.addTravelActivity(travelActivity1);
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //check the plan
        travelActivityList.checkTravelActivity(1);
        TravelActivity travelActivity2 = travelActivityList.getTravelActivities().get(0);
        assertTrue(travelActivity2.getActivityStatus());
        //uncheck the plan
        travelActivityList.uncheckTravelActivity(1);
        assertFalse(travelActivity2.getActivityStatus());
    }

    @Test
    public void trueTest(){
        assertTrue(true);
    }

    @Test
    //basic test for searchKeyword function
    public void findTest () {
        try {
            TravelActivityList travelActivityListNew = new TravelActivityList();
            travelActivityListNew.addTravelActivity(accommodationNew1);
            travelActivityListNew.addTravelActivity(accommodationNew2);
            travelActivityListNew.addTravelActivity(accommodationNew3);
            travelActivityListNew.addTravelActivity(landmarkNew1);
            travelActivityListNew.addTravelActivity(landmarkNew2);
            travelActivityListNew.addTravelActivity(landmarkNew3);
            travelActivityListNew.addTravelActivity(foodNew1);
            travelActivityListNew.addTravelActivity(foodNew2);
            travelActivityListNew.addTravelActivity(foodNew3);
            travelActivityListNew.addTravelActivity(travelActivityNew1);
            travelActivityListNew.addTravelActivity(travelActivityNew2);
            travelActivityListNew.addTravelActivity(travelActivityNew3);
            String[] command1 = new String[]{"find", "mala"};

            String findExpectedOutput = "Here are what you are looking for:" + System.lineSeparator() +
                    "1. Food: utown mala :19 Jun 2019 :2 hours (spicy)" + System.lineSeparator() +
                    "2. Food: pgpr mala :7 Jul 2012 :1 hours (spicy)"  + System.lineSeparator();
            Parser.findCommand(command1, travelActivityListNew);
            assertEquals(capturedOutputStream.toString(), findExpectedOutput);
        } catch (OmniException exception) {
            Ui.printException(exception);
        }


    }

    @Test
    //basic test for searchKeyword function
    public void findTagTest () {
        try {
            TravelActivityList travelActivityListNew = new TravelActivityList();
            travelActivityListNew.addTravelActivity(accommodationNew1);
            travelActivityListNew.addTravelActivity(accommodationNew2);
            travelActivityListNew.addTravelActivity(accommodationNew3);
            travelActivityListNew.addTravelActivity(landmarkNew1);
            travelActivityListNew.addTravelActivity(landmarkNew2);
            travelActivityListNew.addTravelActivity(landmarkNew3);
            travelActivityListNew.addTravelActivity(foodNew1);
            travelActivityListNew.addTravelActivity(foodNew2);
            travelActivityListNew.addTravelActivity(foodNew3);
            travelActivityListNew.addTravelActivity(travelActivityNew1);
            travelActivityListNew.addTravelActivity(travelActivityNew2);
            travelActivityListNew.addTravelActivity(travelActivityNew3);

            String findExpectedOutput2 = "Here are what you are looking for:" + System.lineSeparator() +
                    "1. merlion :7 Apr 2018 :2 hours (sightseeing)" + System.lineSeparator() +
                    "2. chinatown :21 Feb 2015 :5 hours (sightseeing)" + System.lineSeparator();
            Parser.findTagCommand("findtag sightseeing", travelActivityListNew);
            assertEquals(capturedOutputStream.toString(), findExpectedOutput2);

        } catch (OmniException exception) {
            Ui.printException(exception);
        }
    }

    @Test
    //basic test for searchKeyword function
    public void findTypeTest () {
        try {
            TravelActivityList travelActivityListNew = new TravelActivityList();
            travelActivityListNew.addTravelActivity(accommodationNew1);
            travelActivityListNew.addTravelActivity(accommodationNew2);
            travelActivityListNew.addTravelActivity(accommodationNew3);
            travelActivityListNew.addTravelActivity(landmarkNew1);
            travelActivityListNew.addTravelActivity(landmarkNew2);
            travelActivityListNew.addTravelActivity(landmarkNew3);
            travelActivityListNew.addTravelActivity(foodNew1);
            travelActivityListNew.addTravelActivity(foodNew2);
            travelActivityListNew.addTravelActivity(foodNew3);
            travelActivityListNew.addTravelActivity(travelActivityNew1);
            travelActivityListNew.addTravelActivity(travelActivityNew2);
            travelActivityListNew.addTravelActivity(travelActivityNew3);

            String findExpectedOutput3 = "Here are what you are looking for:" + System.lineSeparator() +
                    "1. Accommodation: nus rvrc :12 Dec 2007 :5 years (campus stay)" + System.lineSeparator() +
                    "2. Accommodation: nus pgpr :12 Oct 2017 :5 years (campus stay)" + System.lineSeparator() +
                    "3. Accommodation: nus utr :12 Sep 2007 :5 years (campus stay)" + System.lineSeparator();
            Parser.findTypeCommand("findtype Accommodation", travelActivityListNew);
            assertEquals(capturedOutputStream.toString(), findExpectedOutput3);
            Parser.findTypeCommand("findtype Accommodation", travelActivityListNew);

        } catch (OmniException exception) {
            Ui.printException(exception);
        }
    }


    @Test
    public void testTagActivity() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing"));
        assertEquals("visit museum", list.getDescription("visit museum"));
        // Tagging an existing task
        list.tagActivity(1, "activity 1");
        TravelActivity travelActivity = list.getTravelActivities().get(0);
        assertEquals("activity 1", travelActivity.getTag());
    }

    @Test
    public void testRemoveTagFromActivity() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing"));
        assertEquals("visit museum", list.getDescription("visit museum"));
        // Tagging an existing task
        list.tagActivity(1, "activity 1");
        TravelActivity travelActivity = list.getTravelActivities().get(0);
        assertEquals("activity 1", travelActivity.getTag());
        // Remove an existing tag
        list.removeTag(1);
        assertEquals("visit museum", list.getDescription("visit museum"));
    }

    @Test
    public void testUpdateActivity() throws OmniException{
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("Go Paris",
                LocalDate.parse("2019-02-10"),"2hours", "Sightseeing");
        travelActivityList.addTravelActivity(travelActivity1);
        assertEquals("10 Feb 2019",
                travelActivity1.getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        assertEquals("2hours", travelActivity1.getDuration());
        travelActivityList.updateTravelActivity(1,
                LocalDate.parse("2020-12-10"), "3hours", "misc");
        assertEquals("10 Dec 2020",
                travelActivity1.getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        assertEquals("3hours", travelActivity1.getDuration());
    }



}
