package seedu.voyagers;

public enum Currency {
    USD("US Dollar", "$"),
    EUR("Euro", "€"),
    GBP("British Pound", "£"),
    JPY("Japanese Yen", "¥"),
    AUD("Australian Dollar", "$"),
    CAD("Canadian Dollar", "$"),
    CHF("Swiss Franc", "CHF"),
    CNY("Chinese Yuan Renminbi", "¥"),
    SEK("Swedish Krona", "kr"),
    NZD("New Zealand Dollar", "$"),
    MXN("Mexican Peso", "$"),
    SGD("Singapore Dollar", "$"),
    HKD("Hong Kong Dollar", "$"),
    NOK("Norwegian Krone", "kr"),
    KRW("South Korean Won", "W"),
    TRY("Turkish Lira", "₺"),
    RUB("Russian Ruble", "₽"),
    INR("Indian Rupee", "₹"),
    BRL("Brazilian Real", "R$"),
    ZAR("South African Rand", "R");

    private final String name;
    private final String symbol;

    Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

}


