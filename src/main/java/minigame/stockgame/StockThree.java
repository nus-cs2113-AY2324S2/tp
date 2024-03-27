package minigame.stockgame;

import ui.ResponseManager;

public class StockThree implements Stock{

    private static final String STOCK_GRAPH =
            "▲                                                                \n" +
            "│                                                                \n" +
            "│                                                                \n" +
            "│                                                                \n" +
            "│                                                                \n" +
            "│                                        xxxx                 x  \n" +
            "│                                      xxx  xxx               x  \n" +
            "│                                    xx       xx        xxxxxxx  \n" +
            "│                                   xx         xxx xxxxxx        \n" +
            "│                                  x             xx              \n" +
            "│                       xxx       x                              \n" +
            "│                    xxxx xx    xx                               \n" +
            "│                  xxx     x xxxx                                \n" +
            "│                 x        xxx                                   \n" +
            "│          xxx   x                                               \n" +
            "│        xxx xx x                                                \n" +
            "│       xx    xxx                                                \n" +
            "│      xx                                                        \n" +
            "│     xx                                                         \n" +
            "│     x                                                          \n" +
            "│    xx                                                          \n" +
            "│   x                                                            \n" +
            "│                                                                \n" +
            "│                                                                \n" +
            "└───────────────────────────────────────────────────────────────► \n";

    private static final String STOCK_INFORMATION =
            "Demand for AI chips increases drastically in the current market -CnA \n" +
                    "Nvidia chip is dominating the AI market -Economist \n" +
                    "The board have great trust in Jensen Huang -Ryan from X \n";
    private static final String STOCK_NAME = "Nvidia (Multi-national company) \n";
    private static final String HIDDEN_INFO =
            "Nvidia is negotiating with ARM for acquisition. \n";
    private static final int STOCK_PRICE = 950;

    public void printInfo() {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME);
    }

    public int returnProfit() {
        return getRandomNumber(-20, 400);
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
