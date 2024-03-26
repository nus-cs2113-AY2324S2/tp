package seedu.duke;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidFormatException;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
    @Test
    public void testValidChangeTaskTimingFormat() {
        String validInput = "changeTaskTiming /on monday /index 1 /from 09:00 /to 10:00";
        try {
            InputValidator.validateChangeTaskTiming(validInput);
        } catch (InvalidFormatException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidChangeTaskTimingFormat() {
        String invalidInput = "changeTaskTiming /on monday /index 1 /from 09:00";
        try {
            InputValidator.validateChangeTaskTiming(invalidInput);
            fail("Expected InvalidFormatException");
        } catch (InvalidFormatException e) {
            assertEquals("[ERROR] Invalid changeTaskTiming format. Expected format: " +
                    "changeTaskTiming /on [day] /index [index] /from [new start time] /to [new end time]", e.getMessage());
        }
    }
    @Test
    public void testValidChangeTaskTypeFormat() {
        String validInput = "changetasktype /on Monday /index 1 /type f";
        try {
            InputValidator.validateChangeTaskType(validInput);
        } catch (InvalidFormatException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidChangeTaskTypeFormat() {
        String invalidInput = "changetasktype /on Monday /index 1 /type a";
        try {
            InputValidator.validateChangeTaskType(invalidInput);
            fail("Expected InvalidFormatException");
        } catch (InvalidFormatException e) {
            assertEquals("[ERROR] Invalid changeTaskType format. Expected format: " +
                    "changeTaskType /on [day] /index [index] /type [f/c]", e.getMessage());
        }
    }
    @Test
    public void testValidAddRepeatTaskFormat() {
        String validInput = "addRepeatTask /task lec /on monday tuesday /from 08:00 /to 10:00 /type f";
        try {
            InputValidator.validateAddRepeatTask(validInput);
        } catch (InvalidFormatException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidAddRepeatTaskFormat() {
        String invalidInput = "addRepeatTask /task lec /from 08:00 /to 10:00 /type f";
        try {
            InputValidator.validateAddRepeatTask(invalidInput);
            fail("Expected InvalidFormatException");
        } catch (InvalidFormatException e) {
            assertEquals("[ERROR] Invalid addRepeatTask format. Expected format: " +
                    "addRepeatTask /task [description] /on [day(s)] /from [start time] /to [end time] /type [f/c]", e.getMessage());
        }
    }

}