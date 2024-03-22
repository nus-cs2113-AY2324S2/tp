package seedu.budgetbuddy.command;

import seedu.budgetbuddy.RecurringExpensesList;

import java.util.ArrayList;
import java.util.Arrays;

public class RecurringExpenseCommand extends Command{

    private RecurringExpensesList expensesList;

    private String initialListName;
    private String newListName;

    private String commandType;

    public static ArrayList<String> commandTypes = new ArrayList<>(Arrays.asList("newlist",
            "removelist", "rename"));

    public RecurringExpenseCommand(String initialListName, String newListName, String commandType) {
        this.initialListName = initialListName;
        this.newListName = newListName;
        this.commandType = commandType;
    }

    public RecurringExpenseCommand(String initialListName, String commandType,
                                   RecurringExpensesList expensesList) {
        this.initialListName = initialListName;
        this.commandType = commandType;
        this.expensesList = expensesList;
    }

    public void addNewList(RecurringExpensesList expensesList, String listName) {
        expensesList.addNewRecurringList(listName);
    }
    public void execute() {

        switch(commandType) {
        case "newlist" :
            addNewList(expensesList, initialListName);
            break;
        default:
            break;
        }
    }

}
