package seedu.budgetbuddy.command;

import seedu.budgetbuddy.Expense;
import seedu.budgetbuddy.ExpenseList;
import seedu.budgetbuddy.RecurringExpensesList;
import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.exception.BudgetBuddyException;

import java.util.ArrayList;
import java.util.Arrays;

public class RecurringExpenseCommand extends Command{
    public static ArrayList<String> commandTypes = new ArrayList<>(Arrays.asList("newlist",
            "removelist", "rename", "viewlists", "newexpense", "addrec", "viewexpenses"));

    private RecurringExpensesList expensesList;

    private ExpenseList overallExpenses;
    private String initialListName;
    private String commandType;
    private int listNumber;

    private String category;
    private Double amount;
    private String description;

    private Ui ui = new Ui();


    public RecurringExpenseCommand(RecurringExpensesList expensesList, String commandType) {
        this.commandType = commandType;
        this.expensesList = expensesList;
    }

    public RecurringExpenseCommand(String initialListName,
                                   RecurringExpensesList expensesList, String commandType) {
        this.initialListName = initialListName;
        this.commandType = commandType;
        this.expensesList = expensesList;
    }

    public RecurringExpenseCommand(int listNumber,
                                   RecurringExpensesList expensesList, String commandType) {
        this.listNumber = listNumber;
        this.commandType = commandType;
        this.expensesList = expensesList;
    }

    public RecurringExpenseCommand(int listNumber, RecurringExpensesList expensesList,
                                   ExpenseList overallExpenses, String commandType) {

        this.expensesList = expensesList;
        this.overallExpenses = overallExpenses;
        this.listNumber = listNumber;
        this.commandType = commandType;
    }

    public RecurringExpenseCommand( int listNumber, RecurringExpensesList expensesList, String category,
                                   Double amount, String description, String commandType) {

        this.expensesList = expensesList;
        this.listNumber = listNumber;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.commandType = commandType;
    }


    public void addNewList(String listName) {
        expensesList.addNewRecurringList(listName);
    }

    public void removeList() {

        if (listNumber == 0 || listNumber > expensesList.getSize()) {
            System.out.println("Invalid List Number. Choose a List Number from 1 onwards");
            System.out.println("Number of Lists you have currently : " + expensesList.getSize());
            return;
        }

        expensesList.removeList(listNumber);
    }

    public void addExpenseToList() {

        if (listNumber <= 0 || listNumber > expensesList.getSize()) {
            System.out.println("Invalid List Number. Choose a List Number from 1 onwards");
            System.out.println("Number of Lists you have currently : " + expensesList.getSize());
            return;
        }

        ExpenseList expenses = expensesList.getExpenseListAtListNumber(listNumber);

        try {
            expenses.addExpense(category, amount.toString(), description);

            ui.printDivider();
            System.out.println("Successfully Added Expense to " + expenses.getName());
            ui.printDivider();

        } catch (BudgetBuddyException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addRecurringExpensesToExpenses() {

        if (listNumber <= 0 || listNumber > expensesList.getSize()) {
            System.out.println("Invalid List Number. Choose a List Number from 1 onwards");
            System.out.println("Number of Lists you have currently : " + expensesList.getSize());
            return;
        }

        ExpenseList expenseList = expensesList.getExpenseListAtListNumber(listNumber);
        ArrayList<Expense> expenses = expenseList.getExpenses();

        for (Expense expense : expenses) {
            String category = expense.getCategory();
            Double amount = expense.getAmount();
            String description = expense.getDescription();

            Command addExpenseCommand = new AddExpenseCommand(overallExpenses, category,
                    amount.toString(), description);

            addExpenseCommand.execute();
        }

        ui.printDivider();
        System.out.println("You Recurring Expenses in " + expenseList.getName() +
                "has been added to your overall Expenses");

        ui.printDivider();

    }

    public void printExpensesAtIndex() {

        if (listNumber <= 0 || listNumber > expensesList.getSize()) {
            System.out.println("Invalid List Number. Choose a List Number from 1 onwards");
            System.out.println("Number of Lists you have currently : " + expensesList.getSize());
            return;
        }

        ExpenseList expenseList = expensesList.getExpenseListAtListNumber(listNumber);

        expenseList.listExpenses(null);
    }

    public void printList() {
        expensesList.printAllRecurringLists();
    }
    public void execute(){

        switch(commandType) {
        case "newlist":
            addNewList(initialListName);
            break;

        case "viewlists":
            printList();
            break;

        case "removelist":
            removeList();
            break;

        case "newexpense":
            addExpenseToList();
            break;
        case "addrec":
            addRecurringExpensesToExpenses();
            break;

        case "viewexpenses":
            printExpensesAtIndex();
            break;

        default:
            break;
        }
    }

}
