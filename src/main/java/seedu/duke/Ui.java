package seedu.duke;
import java.util.Scanner;

public class Ui {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();

        // Edit an expense
        editExpense(expenseList, scanner);

        // Edit a saving
        editSaving(savingList, scanner);

        // Close the scanner to avoid resource leak
        scanner.close();
    }

    private static void editExpense(ExpenseList expenseList, Scanner scanner) {
        System.out.println("Enter edit expense command:");
        String editExpenseCommand = scanner.nextLine();

        Transaction editedExpense = Parser.parseEditExpenseCommand(editExpenseCommand);
        if (editedExpense != null) {
            expenseList.editExpense(editedExpense.getCategory(), 1, editedExpense.getAmount(), editedExpense.getDescription());
            System.out.println("Expense edited successfully.");
        } else {
            System.out.println("Invalid edit expense command.");
        }
    }

    private static void editSaving(SavingList savingList, Scanner scanner) {
        System.out.println("Enter edit saving command:");
        String editSavingCommand = scanner.nextLine();

        Transaction editedSaving = Parser.parseEditSavingCommand(editSavingCommand);
        if (editedSaving != null) {
            savingList.editSaving(editedSaving.getCategory(), 1, editedSaving.getAmount(), editedSaving.getDescription());
            System.out.println("Saving edited successfully.");
        } else {
            System.out.println("Invalid edit saving command.");
        }
    }
}
