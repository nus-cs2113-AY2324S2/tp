package grocery;

import exceptions.GitException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class GroceryListTest {
    // exp
    @Test
    public void setExpiration_noSuchGrocery_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.setExpiration("nothing");
        } catch (GitException e) {
            assertEquals("The grocery does not exist!", e.getMessage());
        }
    }

    @Test
    public void setExpiration_wrongFormat_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", "", ""));
            gl.setExpiration("Meat");
        } catch (GitException e) {
            String message = "Command is in the wrong format, type \"help\" for more information." +
                    System.lineSeparator() +
                    "exp needs 'd/'";
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void addGrocery_throwIllegalArgument_exception(){
        try{
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("InvalidGroceryName", null, null));
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e){
            assertEquals("The grocery name is invalid.", e.getMessage());
        }
    }
}
