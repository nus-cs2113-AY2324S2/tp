package florizz.command;


import florizz.core.Ui;
import florizz.objects.Bouquet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpTest {
    @Test
    void execute_helpCommand_true(){
        // next two line might not be necessary
        ArrayList<Bouquet> tempBouquetList = new ArrayList<>();
        Ui ui = new Ui();
        HelpCommand helpCommand = new HelpCommand();
        assertEquals(true, helpCommand.execute(tempBouquetList, ui));
    }
}
