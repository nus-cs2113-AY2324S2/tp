package seedu.budgetbuddy;

public class SplitExpense {
    private final String amount;
    private final String description;
    private final String numberOfPeople;

    public SplitExpense(String amount, String numberOfPeople, String description) {
        this.amount = amount;
        this.numberOfPeople = numberOfPeople;
        this.description = description;
    }

    public String getNumberOfPeople() {
        return numberOfPeople;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public double calculateAmountPerPerson() {
        double amountValue = Double.parseDouble(amount);
        double numberOfPeopleValue = Double.parseDouble(numberOfPeople);
        return amountValue / numberOfPeopleValue;
    }

    @Override
    public String toString() {
        return "Number of People: " + numberOfPeople + " Amount: " + amount + " Description: " +
                description + " Amount per person: " + calculateAmountPerPerson();
    }
}
