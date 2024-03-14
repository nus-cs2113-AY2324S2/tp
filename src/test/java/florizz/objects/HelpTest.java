package florizz.objects;

import florizz.command.HelpCommand;
import florizz.core.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpTest {
    @Test
    void execute_helpCommand_true(){
        // next two line might not be necessary
        ArrayList<Bouquet> tempBouquetList = new ArrayList<>();
        Ui ui = new Ui();
        HelpCommand HelpCommand = new HelpCommand();
        assertEquals(true, HelpCommand.execute(tempBouquetList, ui));
    }
}
