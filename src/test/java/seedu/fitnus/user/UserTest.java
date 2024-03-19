package seedu.fitnus.user;

import org.junit.jupiter.api.Test;
import seedu.fitnus.Drink;
import seedu.fitnus.Meal;
import seedu.fitnus.Parser;
import seedu.fitnus.Water;
import seedu.fitnus.exception.IncompleteMealException;
import seedu.fitnus.exception.IncompleteWaterException;
import seedu.fitnus.exception.UnregisteredMealException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    ArrayList<Meal> testMealList = new ArrayList<>();
    ArrayList<Drink> testDrinkList = new ArrayList<>();

    @Test
    public void sampleUser() {
        assertTrue(true);
    }

    @Test
    public void handleMeal_validInputs_correctlyAddMeal() throws IncompleteMealException, UnregisteredMealException {
        Meal newMeal = new Meal("pizza", 3);
        testMealList.add(newMeal);

        assertEquals("pizza", testMealList.get(0).getName());
        assertEquals(3, testMealList.get(0).getServingSize());
    }

    @Test
    public void handleWater_unknownServingSize_addWaterFailed() throws IncompleteWaterException {
        try {
            Parser.parseWater("water 1");
            new Water(Parser.waterSize);
        } catch (IncompleteWaterException e) {
            return;
        }

        String error = "Incomplete command, the format must be [water s/SERVING_SIZE].";
        fail(error);
    }
}
