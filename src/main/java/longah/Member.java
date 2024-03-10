package longah;

public class Member {
    private String name;
    private double balance;

    public Member(String name) {
        this.name = name;
        this.balance = 0.0;
    }

    public void addToBalance(double amount) {
        balance += amount;
    }

    public void subtractFromBalance(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return name + ": $" + balance;
    }

    public String getName() {
        return name;
    }
}
