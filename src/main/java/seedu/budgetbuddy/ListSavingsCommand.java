package seedu.budgetbuddy;

public class ListSavingsCommand extends Command {
    private SavingList savings;
    private ExpenseList expenses;
    private String filterCategory;

    public ListSavingsCommand(SavingList savings, ExpenseList expenses) {
        this.savings = savings;
        this.expenses = expenses;
    }

    public ListSavingsCommand(SavingList savings, ExpenseList expenses, String filterCategory) {
        this.savings = savings;
        this.expenses = expenses;
        this.filterCategory = filterCategory;
    }

    @Override
    public void execute() {
        savings.listSavings(this.filterCategory, this.expenses);
    }
}

