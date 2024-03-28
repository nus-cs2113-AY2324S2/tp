package seedu.budgetbuddy;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.MenuCommand;
import seedu.budgetbuddy.commandcreator.CommandCreator;
import seedu.budgetbuddy.commandcreator.MenuCommandCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class MenuCommandCreatorTest {
    @Test
    @Disabled
    public void createMenuCommand_menuCommandWithNoIndex_success() {
        CommandCreator commandCreator = new MenuCommandCreator("menu");
        Command command = commandCreator.createCommand();

        assertInstanceOf(MenuCommand.class, command);
        assertEquals(0,((MenuCommand)command).getIndex());
    }

    @Test
    public void createMenuCommand_menuCommandwithValidIndex_success() {
        CommandCreator commandCreator = new MenuCommandCreator("menu 1");
        Command command = commandCreator.createCommand();

        assertInstanceOf(MenuCommand.class, command);
        assertEquals(1,((MenuCommand)command).getIndex());
    }

}
