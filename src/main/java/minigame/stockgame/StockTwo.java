package minigame.stockgame;

import ui.ResponseManager;

public class StockTwo implements Stock{
    private static final String STOCK_GRAPH =
                    "▲                                                         \n" +
                    "│                                                  xxx    \n" +
                    "│                                                xx       \n" +
                    "│                                                x        \n" +
                    "│                                               xx        \n" +
                    "│                                               x         \n" +
                    "│                                            xxxx         \n" +
                    "│                                            x            \n" +
                    "│                                            x            \n" +
                    "│                                           xx            \n" +
                    "│                                          xx             \n" +
                    "│                      xxx               xxx              \n" +
                    "│                     x  xx         x   xx                \n" +
                    "│          xx        xx   xx     xxxxx xx                 \n" +
                    "│         xxxx    xxx      xxxxxx    xxx                  \n" +
                    "│         x  xxxxxx                                       \n" +
                    "│        x                                                \n" +
                    "│      xxx                                                \n" +
                    "│ xxxxxx                                                  \n" +
                    "│                                                         \n" +
                    "└────────────────────────────────────────────────────────►";
    private static final String STOCK_INFORMATION =
            "Demand for robots have risen in multiple sectors. -CNN \n"
                    + "Will Elon Musk expand Tesla's robotic industry? -Economists \n"
                    + "I work at Tesla and I recently received a raise -User634786 from X \n";
    private static final String STOCK_NAME = "Tesla (Large multi-national company) \n";

    private static final String HIDDEN_INFO = "Elon musk will soon announce " +
            "to increase Tesla's robotic industry\n";

    private static final int STOCK_PRICE = 170;

    public void printInfo() {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME);
    }

    public int returnProfit() {
        return getRandomNumber(10, 30);
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
