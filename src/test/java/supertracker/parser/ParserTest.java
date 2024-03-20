package supertracker.parser;

import org.junit.jupiter.api.Test;
import supertracker.TrackerException;
import supertracker.command.Command;
import supertracker.command.InvalidCommand;
import supertracker.command.NewCommand;
import supertracker.command.UpdateCommand;
import supertracker.command.QuitCommand;
import supertracker.item.Inventory;
import supertracker.item.Item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    @Test
    public void parseCommand_validNewCommandInput_newCommand() throws TrackerException {
        String[] inputs = {"new n/apple q/50 p/99.5", "new p/99.5 q/23 n/elephant", "new q/88 n/banana p/213"};

        for (String input : inputs) {
            Command resultCommand = Parser.parseCommand(input);
            assertInstanceOf(NewCommand.class, resultCommand);
        }
    }

    @Test
    public void parseCommand_validUpdateCommandInput_updateCommand() throws TrackerException {
        Command newItem = Parser.parseCommand("new n/banana milkshake q/11 p/12.2");
        newItem.execute();

        Command update = Parser.parseCommand("update n/banana milkshake q/15 p/9.11");
        assertInstanceOf(UpdateCommand.class, update);
        update.execute();
        Item bShake = Inventory.get("banana milkshake");
        assertEquals(15, bShake.getQuantity());
        assertEquals(9.11, bShake.getPrice());

        update = Parser.parseCommand("update n/banana milkshake q/6969");
        update.execute();
        bShake = Inventory.get("banana milkshake");
        assertEquals(6969, bShake.getQuantity());

        update = Parser.parseCommand("update n/banana milkshake p/96.96");
        update.execute();
        bShake = Inventory.get("banana milkshake");
        assertEquals(96.96, bShake.getPrice());
    }

    @Test
    public void parseCommand_validDeleteCommandInput_deleteCommand() throws TrackerException {

        Command newItem = Parser.parseCommand("new n/strawberry q/12 p/2.2");
        newItem.execute();

        Command deleteItem = Parser.parseCommand("delete n/strawberry");
        deleteItem.execute();

        assertFalse(Inventory.contains("strawberry"));
    }

    @Test
    public void parseCommand_invalidCommandInput_invalidCommand() throws TrackerException {

        String[] inputs = {"abcdefg", "1239", "newnew n/j q/2 p/123", "elephant"};

        for (String input : inputs) {
            Command resultCommand = Parser.parseCommand(input);
            assertInstanceOf(InvalidCommand.class, resultCommand);
        }
    }

    @Test
    public void parseCommand_validQuitCommandInput_quitCommand() throws TrackerException {

        String input = "quit";
        Command resultCommand = Parser.parseCommand(input);

        assertInstanceOf(QuitCommand.class, resultCommand);
    }
}
