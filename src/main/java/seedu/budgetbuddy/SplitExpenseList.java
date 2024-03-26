package seedu.budgetbuddy;

import java.util.ArrayList;
import java.util.List;

import seedu.budgetbuddy.exception.BudgetBuddyException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SplitExpenseList {
    private static final Logger LOGGER = Logger.getLogger(SplitExpenseList.class.getName());
    protected ArrayList <SplitExpense> splitexpenses;
    public SplitExpenseList(ArrayList<SplitExpense> splitexpenses){
        this.splitexpenses = splitexpenses;
    }

    public SplitExpenseList() {
        this.splitexpenses = new ArrayList<>();
    }

    public int size() {
        return splitexpenses.size();
    }

    public List<SplitExpense> getSplitExpenses() {
        return splitexpenses;
    }

    public void listSplitExpenses() {
        LOGGER.info("Listing splitexpenses...");

        try {
            System.out.println("Split Expenses: ");
            for (int i = 0; i < splitexpenses.size(); i++) {
                SplitExpense splitexpense = splitexpenses.get(i);

                if (splitexpense == null) {
                    LOGGER.warning("Expense object at index " + i + " is null");
                    continue;
                }
                System.out.print(i+1 + " | ");
                System.out.print("Amount: " + splitexpense.getAmount());
                System.out.print(" Number of People: " + splitexpense.getNumberOfPeople());
                System.out.print(" Description: " + splitexpense.getDescription());
                System.out.println(" Amount per person: " + splitexpense.calculateAmountPerPerson());
            }
            System.out.println("-----------------------------------------------------------------------------");
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while listing expenses.", e);
        }
    }

    public void addSplitExpense(String amount, String numberOfPeople, String description ) throws BudgetBuddyException {
        assert amount != null : "Amount should not be null";
        assert description != null : "Description should not be null";
        LOGGER.info("Adding split expense...");

        double amountDouble;
        try{
            amountDouble = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            throw new BudgetBuddyException("Invalid amount format. Amount should be a number.");
        }

        if (amountDouble < 0){
            throw new BudgetBuddyException("Expenses should not be negative.");
        }

        SplitExpense splitexpense = new SplitExpense(amount, numberOfPeople, description);
        splitexpenses.add(splitexpense);
    }
}
