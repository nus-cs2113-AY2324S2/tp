package ui;

import utility.Constant;

public class Output {
    /**
     * Prints a horizontal line.
     */
    public static void printLine() {
        System.out.println(Constant.PARTITION_LINE);
    }

    /**
     * Prints the help message.
     */
    public static void printHelp() {
        printLine();
        System.out.println("Commands List:");
        System.out.println();
        System.out.println("list - prints out the List");
        System.out.println("help - procures command list");
        System.out.println("exit - terminates the bot");
        printLine();
        System.out.println("bmi - calculates BMI");
        System.out.println("weight - save current weight");
        System.out.println("height - save current height");
        printLine();
        System.out.println("bmi format: bmi *parameter*");
        printLine();
    }
}
