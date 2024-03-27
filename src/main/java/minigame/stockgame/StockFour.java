package minigame.stockgame;

import ui.ResponseManager;

public class StockFour implements Stock{

    public static final String STOCK_INFORMATION =
            "Demand for AI chip risen in multiple sectors. -CNN \n"
                    + "GROQ AI chips, at what cost? -Economists \n"
                    + "We are going to have a huge progress -Jack786 from X \n";;
    public static final String STOCK_NAME = "Groq (Start up company)";
    public static final String HIDDEN_INFO = "The progress of chip " +
            "development currently is not too well";

    public static final int STOCK_PRICE = 23;
    private static final String STOCK_GRAPH =
            "▲                                                           \n" +
            "│                                                           \n" +
            "│                                                           \n" +
            "│                                                           \n" +
            "│                   xx                                      \n" +
            "│                  xxxx                                     \n" +
            "│                 xx  x                                     \n" +
            "│        xxx     xx    x                                    \n" +
            "│       xx xx   xx     x                                    \n" +
            "│       x    xxxx      xx                                 x \n" +
            "│      x                xx             xx                xx \n" +
            "│ xxxxx                  xx           xxxxx       xxxxxxxx  \n" +
            "│                          xx         x   xx     xx         \n" +
            "│                           xx        x    xxxxxx           \n" +
            "│                            xx       x                     \n" +
            "│                             xx     xx                     \n" +
            "│                              xxxxxxx                      \n" +
            "│                                                           \n" +
            "│                                                           \n" +
            "│                                                           \n" +
            "│                                                           \n" +
            "└──────────────────────────────────────────────────────────►\n";


    public void printInfo() {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME);
    }

    public int returnProfit() {
        return getRandomNumber(-20, -3);
    }

    public int investmentGain(int stockAmount) {
        int gainPerStock = returnProfit();
        int gain = gainPerStock * stockAmount;
        System.out.println("The stock price risen by: " + gainPerStock);
        System.out.println("Your gain in stock for this round is: " + gain);
        return gain;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
