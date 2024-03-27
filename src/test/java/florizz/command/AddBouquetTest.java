package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBouquetTest {
    @Test
    void testAddCommandExecute(){
        Bouquet testBouquet = new Bouquet("for testing");
        Ui ui = new Ui();
        ArrayList<Bouquet> controlList = new ArrayList<>();
        ArrayList<Bouquet> testList = new ArrayList<>();
        controlList.add(testBouquet);
        Command testAddBouquetCommand = new AddBouquetCommand(testBouquet);
        try {
            testAddBouquetCommand.execute(testList, ui);
        } catch(FlorizzException error){
            ui.printError(error);
        }
        assertEquals(controlList, testList);
    }
}
