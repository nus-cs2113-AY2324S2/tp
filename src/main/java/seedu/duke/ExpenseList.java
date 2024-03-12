package seedu.duke;
import java.util.ArrayList;

public class ExpenseList {
    protected ArrayList<Expense> expenses = new ArrayList<>();
    protected ArrayList<String> categories = new ArrayList<>();

    public void deleteExpense(int index){
        if (index >= 0 && index < expenses.size()){
            expenses.remove(index);
        } else {
            System.out.println("Invalid expense index.");
        }
    }
}
