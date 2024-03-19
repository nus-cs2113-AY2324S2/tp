package ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ui.UiRenderer.printTaskForDay;

public class UiRendererTest {
    
    public static List<String> tasks = List.of("task1", "task2", "task3", "task4");
    public static List<String> emptyTaskList = List.of();    
    private static final int SPACE_COUNT = 15;
    private static final String VERTICAL_DIVIDER = "|";
    private static final String TASK_DISPLAY_FORMAT = VERTICAL_DIVIDER + " %-" + SPACE_COUNT + "." + SPACE_COUNT + "s ";
    private static final String EMPTY_TASK_DISPLAY_FORMAT = VERTICAL_DIVIDER + " ".repeat(SPACE_COUNT+1) + " ";
    
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void printTaskForDay_noTasks_printsNoTasks() {
        printTaskForDay(emptyTaskList, 0);
        assertTrue(outContent.toString().equals(EMPTY_TASK_DISPLAY_FORMAT));
    }

    @Test
    void printTaskForDay_hasTask_printsTaskWithFormat() {
        printTaskForDay(tasks, 0);
        assertTrue(outContent.toString().equals(String.format(TASK_DISPLAY_FORMAT, tasks.get(0))));
    }
    
}
