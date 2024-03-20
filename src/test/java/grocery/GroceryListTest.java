package grocery;

import exceptions.EmptyGroceryException;
import exceptions.GitException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class GroceryListTest {
    @Test
    public void setExpiration_success() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", "", ""));
            gl.setExpiration("Meat d/ Monday");
        } catch (GitException e) {
            fail("setExpiration should be successful");
        }
    }

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
    public void addGrocery_throwIllegalArgument_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery(null, null, null)); // Use null to trigger the exception
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (EmptyGroceryException e) {
            assertEquals("A grocery needs to be specified!", e.getMessage());
        }
    }

    @Test
    public void removeGrocery_groceryDelete_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("fooood", null, null));
            gl.removeGrocery("food");
            fail("Expected NoSuchGroceryException not thrown");
        } catch (GitException e) {
            // NoSuchGroceryException
            assertEquals("The grocery does not exist!", e.getMessage());
        }
    }

    @Test
    public void setAmount_wrongFormat_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", "", ""));
            gl.setAmount("Meat");
        } catch (GitException e) {
            String message = "Command is in the wrong format, type \"help\" for more information." +
                    System.lineSeparator() +
                    "amt needs 'a/'";
            assertEquals(message, e.getMessage());
        }
    }


}
