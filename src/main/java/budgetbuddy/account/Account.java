package budgetbuddy.account;

public class Account {
    private double balance;

    public Account() {
        this.balance = 0.00;
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
