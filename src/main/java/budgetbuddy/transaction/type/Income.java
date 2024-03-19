package budgetbuddy.transaction.type;

import budgetbuddy.account.Account;

public class Income extends Transaction {
    private static final String TRANSACTION_TYPE = "Income";

    public Income(String description, double amount, String category, String date, Account account) {
        super(description, amount, category, date);
        account.setBalance(account.getBalance() + this.getAmount());
    }

    @Override
    public String getTransactionType() {
        return TRANSACTION_TYPE;
    }
}
