package minigame.stockgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockStorage {
    private final List<Stock> stocksAvailable = new ArrayList<>();

    private void setUp() {
        stocksAvailable.add(new StockOne());
        stocksAvailable.add(new StockTwo());
        stocksAvailable.add(new StockThree());
        stocksAvailable.add(new StockFour());
        stocksAvailable.add(new StockFive());
    }

    public void play() {
        setUp();
        Scanner scanner = new Scanner(System.in);
        int index = getRandomNumber(0, stocksAvailable.size() - 1);
        Stock current = stocksAvailable.get(index);
        current.printInfo();
        System.out.println("How many stock do you want to purchase?");
        int response = Integer.parseInt(scanner.nextLine());
        int totalGain = current.investmentGain(response);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
