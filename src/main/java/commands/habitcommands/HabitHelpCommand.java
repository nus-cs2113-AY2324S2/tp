package commands.habitcommands;

import commands.Command;
import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

public class HabitHelpCommand implements Command {
    private static final String[] HELP_MENU_INSTRUCTIONS = {
        "habit add <habit_description>: Add a new habit",
        "habit list: List out all existing habits",
        "habit update /id <habit_ID> /by <increment_count>: Increase habit count after completing a habit",
        "habit delete /id <habit_ID>: Delete a habit",
        "habit set /id <habit_ID> /priority <priority_level>: Set priority level for habits (HIGH, MED, LOW)",
        "habit sort: Sort habit list according to priority level",
    };

    public void execute() {
        ArrayList<String> helpMenuInstructionsList = new ArrayList<>(Arrays.asList(HELP_MENU_INSTRUCTIONS));

        assert helpMenuInstructionsList.size() == 6 : "Help menu should have 6 instructions";

        Ui.printList(helpMenuInstructionsList, "Commands for habit tracker feature:");
    }

    public boolean isExit() {
        return false;
    }
}
