package budgetbuddy.transaction.type;

import budgetbuddy.account.Account;

public class Expense extends Transaction {

    public Expense(String description, double amount, String category, String date, Account account) {
        super(description, amount, category, date);
        account.setBalance(account.getBalance() - this.getAmount());
    }
}
