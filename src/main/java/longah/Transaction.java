package longah;

public class Transaction {
    private Member from;
    private Member to;
    private double amount;

    public Transaction(Member from, Member to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        from.subtractFromBalance(amount);
        to.addToBalance(amount);
    }

    public boolean getInvolves(String person) {
        return from.toString().equals(person) || to.toString().equals(person);
    }

    @Override
    public String toString() {
        return from + " owes " + to + " $" + amount;
    }

    public Member getFrom() {
        return from;
    }

    public Member getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }
}

