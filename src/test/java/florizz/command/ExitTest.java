package florizz.command;

import florizz.command.ExitCommand;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitTest {
    @Test
    void execute_exitCommand_false() {
        // next two line might not be necessary
        ArrayList<Bouquet> tempBouquetList = new ArrayList<>();
        Ui ui = new Ui();
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(false, exitCommand.execute(tempBouquetList, ui));
    }
}
