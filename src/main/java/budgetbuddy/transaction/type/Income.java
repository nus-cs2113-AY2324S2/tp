package budgetbuddy.transaction.type;

import budgetbuddy.account.Account;

public class Income extends Transaction {
    private static final String TRANSACTION_TYPE = "Income";

    public Income(String description, double amount, String category, String date, Account account) {
        super(description, amount, category, date);
        assert this.getAmount() > 0: "Income amount must be positive";
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert category != null && !category.isEmpty() : "Category cannot be null or empty";
        assert date != null : "Date cannot be null";
        assert account != null : "Account cannot be null";

        account.setBalance(account.getBalance() + this.getAmount());
    }

    @Override
    public String getTransactionType() {
        return TRANSACTION_TYPE;
    }
}
