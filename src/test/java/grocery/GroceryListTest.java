package grocery;

import exceptions.commands.EmptyGroceryException;
import exceptions.GitException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class GroceryListTest {
    @Test
    public void editExpiration_success() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", 0, ""));
            gl.editExpiration("Meat d/ Monday");
        } catch (GitException e) {
            fail("setExpiration should be successful");
        }
    }

    @Test
    public void editExpiration_noSuchGrocery_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.editExpiration("nothing");
        } catch (GitException e) {
            assertEquals("The grocery does not exist!", e.getMessage());
        }
    }

    @Test
    public void editExpiration_wrongFormat_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", 0, ""));
            gl.editExpiration("Meat");
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
            gl.addGrocery(new Grocery(null, 0, null)); // Use null to trigger the exception
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (EmptyGroceryException e) {
            assertEquals("A grocery needs to be specified!", e.getMessage());
        }
    }

    @Test
    public void removeGrocery_groceryDelete_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("fooood", 0, null));
            gl.removeGrocery("food");
            fail("Expected NoSuchGroceryException not thrown");
        } catch (GitException e) {
            // NoSuchGroceryException
            assertEquals("The grocery does not exist!", e.getMessage());
        }
    }

    @Test
    public void editAmount_wrongFormat_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", 0, ""));
            gl.editAmount("Meat", false);
        } catch (GitException e) {
            String message = "Command is in the wrong format, type \"help\" for more information." +
                    System.lineSeparator() +
                    "amt needs 'a/'";
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void editAmountUseTrue_amountReaches0_success() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", 5, ""));
            gl.editAmount("Meat a/10", true);
        } catch (GitException e) {
            fail("editAmount_useTrue should be able to handle cases where amount <= 0");
        }
    }

    @Test
    public void editAmountUseTrue_noAmountCannotUse_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", 0, ""));
            gl.editAmount("Meat a/5", true);
        } catch (GitException e) {
            String message = "The grocery you want to use is already out of stock - time to replenish!";
            assertEquals(message, e.getMessage());
        }
    }



}
