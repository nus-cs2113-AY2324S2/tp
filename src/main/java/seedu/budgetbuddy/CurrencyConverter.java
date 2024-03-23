package seedu.budgetbuddy;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private Map<Currency, Double> exchangeRates;

    public CurrencyConverter() {
        this.exchangeRates = new HashMap<>();
        // Initialize exchange rates with default values
        exchangeRates.put(Currency.getInstance("SGD"), 1.0);
        exchangeRates.put(Currency.getInstance("USD"), 0.75);
        exchangeRates.put(Currency.getInstance("EUR"), 0.68);
        exchangeRates.put(Currency.getInstance("JPY"), 112.25);
        exchangeRates.put(Currency.getInstance("KRW"), 996.85);
        exchangeRates.put(Currency.getInstance("MYR"), 3.51);
        exchangeRates.put(Currency.getInstance("CNY"), 5.36);
        exchangeRates.put(Currency.getInstance("HKD"), 5.80);
    }

    public double convertAmount(double amount, Currency fromCurrency, Currency toCurrency) {
        double fromRate = exchangeRates.getOrDefault(fromCurrency, 1.0);
        double toRate = exchangeRates.getOrDefault(toCurrency, 1.0);

        if (!fromCurrency.equals(toCurrency)) {
            // Convert to SGD first
            double amountInSGD = amount / fromRate;
            // Then convert from SGD to the target currency
            return amountInSGD * toRate;
        } else {
            // If the currencies are the same, no conversion needed
            return amount;
        }
    }

    public void convertCurrency(Currency newCurrency, ExpenseList expenses) {
        for (Expense expense : expenses.getExpenses()) {
            double convertedAmount = convertAmount(expense.getAmount(), expense.getCurrency(), newCurrency);
            expense.setAmount(convertedAmount);
            expense.setCurrency(newCurrency);
        }
    }

    public void convertCurrency(Currency newCurrency, SavingList savings) {
        for (Saving saving : savings.getSavings()) {
            double convertedAmount = convertAmount(saving.getAmount(), saving.getCurrency(), newCurrency);
            saving.setAmount(convertedAmount);
            saving.setCurrency(newCurrency);
        }
    }
}
