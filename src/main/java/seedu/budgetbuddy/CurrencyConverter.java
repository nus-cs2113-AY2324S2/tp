package seedu.budgetbuddy;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CurrencyConverter {

    private static final Logger LOGGER = Logger.getLogger(CurrencyConverter.class.getName());
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
        // Check if exchange rates for both currencies are available
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            LOGGER.warning("Exchange rates not available for one or more currencies");
            throw new IllegalArgumentException("Exchange rates not available for one or more currencies");
        }
        assert exchangeRates.containsKey(fromCurrency) : "Exchange rates not available for fromCurrency: "
                + fromCurrency;
        assert exchangeRates.containsKey(toCurrency) : "Exchange rates not available for toCurrency: " + toCurrency;


        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);

        if (fromRate <= 0 || toRate <= 0) {
            LOGGER.warning("Exchange rates must be positive numbers");
            throw new IllegalArgumentException("Exchange rates must be positive numbers");
        }
        // Check if exchange rates are positive numbers
        assert fromRate > 0 : "Exchange rate for fromCurrency must be a positive number: " + fromRate;
        assert toRate > 0 : "Exchange rate for toCurrency must be a positive number: " + toRate;

        LOGGER.info("Converting " + amount + " " + fromCurrency + " to " + toCurrency);

        if (!fromCurrency.equals(toCurrency)) {
            // Convert to SGD first
            double amountInSGD = amount / fromRate;
            // Then convert from SGD to the target currency
            double convertedAmount = amountInSGD * toRate;
            LOGGER.info("Conversion successful. Result: " + convertedAmount + " " + toCurrency);
            return convertedAmount;
        } else {
            // If the currencies are the same, no conversion needed
            LOGGER.info("Same currency. No conversion needed.");
            return amount;
        }
    }

    // Convert currencies in Expense List
    public void convertCurrency(Currency newCurrency, ExpenseList expenses) {
        // Check if the ExpenseList is not null
        if (expenses == null) {
            throw new IllegalArgumentException("ExpenseList cannot be null");
        }
        assert expenses != null : "ExpenseList cannot be null";

        for (Expense expense : expenses.getExpenses()) {
            // Skip if the current expense is null
            if (expense == null) {
                LOGGER.warning("Skipping null expense");
                System.out.println("Skipping null expense");
                continue;
            }

            try {
                double convertedAmount = convertAmount(expense.getAmount(), expense.getCurrency(), newCurrency);
                expense.setAmount(convertedAmount);
                expense.setCurrency(newCurrency);
            } catch (IllegalArgumentException e) {
                // Handle any IllegalArgumentException thrown during conversion
                LOGGER.severe("Error converting amount for expense: " + e.getMessage());
                System.out.println("Error converting amount for expense: " + e.getMessage());
            }
        }
    }

    // Convert currencies in Saving List
    public void convertCurrency(Currency newCurrency, SavingList savings) {
        // Check if the SavingList is not null
        if (savings == null) {
            throw new IllegalArgumentException("SavingList cannot be null");
        }
        assert savings != null : "SavingList cannot be null";

        for (Saving saving : savings.getSavings()) {
            // Skip if the current saving is null
            if (saving == null) {
                LOGGER.warning("Skipping null saving");
                System.out.println("Skipping null saving");
                continue;
            }

            try {
                double convertedAmount = convertAmount(saving.getAmount(), saving.getCurrency(), newCurrency);
                saving.setAmount(convertedAmount);
                saving.setCurrency(newCurrency);
            } catch (IllegalArgumentException e) {
                // Handle any IllegalArgumentException thrown during conversion
                LOGGER.severe("Error converting amount for saving: " + e.getMessage());
                System.out.println("Error converting amount for saving: " + e.getMessage());
            }
        }
    }

}
