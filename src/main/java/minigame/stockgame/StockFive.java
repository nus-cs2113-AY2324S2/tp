package minigame.stockgame;

import ui.ResponseManager;

public class StockFive implements Stock {
    public static final String STOCK_GRAPH =
            "                                              xxxx      \n" +
                    "▲                                    xx     xxx x       \n" +
                    "│                                    xxx  xxx   x       \n" +
                    "│                                   xx xxxx     x    x  \n" +
                    "│                                  xx           xx  xx  \n" +
                    "│                                xxx             xxxx   \n" +
                    "│                              xxx                xx    \n" +
                    "│                           xxx                         \n" +
                    "│                         xxx                           \n" +
                    "│                        xx                             \n" +
                    "│                        x                              \n" +
                    "│                        x                              \n" +
                    "│                        xx                             \n" +
                    "│                xx       x                             \n" +
                    "│              xxxx       x                             \n" +
                    "│              x   xx     x                             \n" +
                    "│             x     x    x                              \n" +
                    "│       xxxxxxx     x   x                               \n" +
                    "│       xxx         xxxx                                \n" +
                    "│      xx            xx                                 \n" +
                    "│      xx                                               \n" +
                    "│    xxxx                                               \n" +
                    "│  xxxxx                                                \n" +
                    "│                                                       \n" +
                    "└──────────────────────────────────────────────────────► \n";
    public static final String STOCK_INFORMATION =
            "Atlas tech have reached its highest point, a 100 times increase -CnA \n" +
                    "Atlas tech, great discover or great scam? -Economist \n" +
                    "I earned a house from buying this stock -Joe from X \n";
    public static final String STOCK_NAME= "Atlas Tech \n";
    public static final String HIDDEN_INFO = "This is likely to be a set up -From a friend\n";
    public static final int STOCK_PRICE = 128;

    public void printInfo() {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME);
    }

    public int returnProfit() {
        return getRandomNumber(-100, -50);
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
