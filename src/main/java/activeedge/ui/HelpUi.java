package activeedge.ui;

/**
 * The HelpUi class provides a user interface for displaying help messages related to
 * the Health Tracker Bot. It outlines how to use various commands within the bot for
 * tracking calorie intake, water intake, and setting personal health goals.
 */
public class HelpUi {

    /**
     * Prints a detailed help message to the standard output.
     */
    public static void printHelpMessage() {
        System.out.println(" Welcome to the Health Tracker Bot! Here are the available commands:\n" +
                "\n" +
                "1. Calorie Intake Tracker:\n" +
                "   - log m/MEAL_NAME s/number of servings: log meals\n" +
                "     Example: log m/chicken rice s/1\n" +
                "   - list meals: view meals\n" +
                "   - show c: view daily calories: \n" +
                "   - set goal c/NUMBER: set daily calorie goal: \n" +
                "     Example: set goal c/2000\n" +
                "\n" +
                "2. Water Intake Tracker:\n" +
                "   - add w/QUANTITY: log water\n" +
                "     Example: add w/500 (logs 500ml of water)\n" +
                "   - show water intake: view water intake\n" +
                "   - set goal w/NUMBER: set daily water goal\n" +
                "     Example: set goal w/2000\n" +
                "\n" +
                "3. Goal Setting:\n" +
                "   - show goals: view goals\n" +
                "\n" +
                "Additional Commands:\n" +
                "   - help: Shows this user guide.\n" +
                "   - list: Lists all logged data for the day (meals and water).\n" +
                "   - clear: Clears all data for the current day.\n" +
                "\n" +
                "Start tracking your health goals now! If you have any questions, feel free to ask." );
    }
}
