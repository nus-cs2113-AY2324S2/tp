package ui;

import data.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import time.MonthView;
import time.WeekView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeekViewTest {

    private WeekView weekView;

    private MonthView monthView;

    private TaskManager taskManagerMock;

    @BeforeEach
    public void setUp() {
        LocalDate startOfWeek = LocalDate.of(2024, 3, 10); // March 10, 2024
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        weekView = new WeekView(startOfWeek, dateFormatter);
        LocalDate startOfMonth = LocalDate.of(2024, 3, 10); // March 10, 2024
        monthView = new MonthView(startOfWeek, dateFormatter);
        taskManagerMock = new TaskManager(); // You can create a mock TaskManager as needed
    }

    @Test
    public void testNextWeek() {
        LocalDate initialStartOfWeek = weekView.getStartOfWeek();
        weekView.next();
        LocalDate newStartOfWeek = initialStartOfWeek.plusWeeks(1);
        assertEquals(newStartOfWeek, weekView.getStartOfWeek());
    }

    @Test
    public void testPreviousWeek() {
        LocalDate initialStartOfWeek = weekView.getStartOfWeek();
        weekView.previous();
        LocalDate newStartOfWeek = initialStartOfWeek.minusWeeks(1);
        assertEquals(newStartOfWeek, weekView.getStartOfWeek());
    }

    @Test
    public void testNextMonth() {
        LocalDate initialStartOfWeek = monthView.getStartOfMonth();
        monthView.next();
        LocalDate newStartOfWeek = initialStartOfWeek.plusMonths(1).withDayOfMonth(1);
        assertEquals(newStartOfWeek, monthView.getStartOfMonth());
    }

    @Test
    public void testPreviousMonth() {
        LocalDate initialStartOfWeek = monthView.getStartOfMonth();
        monthView.previous();
        LocalDate newStartOfWeek = initialStartOfWeek.minusMonths(1).withDayOfMonth(1);
        assertEquals(newStartOfWeek, monthView.getStartOfMonth());
    }

}

