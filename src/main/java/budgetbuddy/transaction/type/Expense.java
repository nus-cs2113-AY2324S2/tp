package budgetbuddy.transaction.type;

import budgetbuddy.account.Account;

public class Expense extends Transaction {
    private static final String TRANSACTION_TYPE = "Expense";

    public Expense(String description, double amount, String date, Account account) {
        super(description, -amount, date);
        assert this.getAmount() < 0: "Expense amount must be positive";
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert date != null : "Date cannot be null";
        assert account != null : "Account cannot be null";

        account.setBalance(account.getBalance() + this.getAmount());
    }

    @Override
    public String getTransactionType() {
        return TRANSACTION_TYPE;
    }
}
