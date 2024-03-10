package longah;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * LongAh class manages debts between members.
 */
public class LongAh {
    private Map<String, Member> members;
    private TransactionList transactions;
    private Scanner scanner;

    /**
     * Constructs a new LongAh instance.
     */
    public LongAh() {
        this.members = new HashMap<>();
        this.transactions = new TransactionList();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Adds a debt between two members.
     *
     * @param person1 The name of the first person.
     * @param amount  The amount of the debt.
     * @param person2 The name of the second person.
     */
    public void addDebt(String person1, double amount, String person2) {
        Member from = getOrCreateMember(person1);
        Member to = getOrCreateMember(person2);
        transactions.add(new Transaction(from, to, amount));
    }

    /**
     * Lists all debts between members.
     */
    public void listAllDebts() {
        Map<String, Double> balances = transactions.calculateBalances();

        // Display simplified debts
        boolean hasDebts = false;
        for (String name : balances.keySet()) {
            double amount = balances.get(name);
            if (amount != 0) {
                String formattedAmount = String.format("%.2f", Math.abs(amount));
                String otherPerson = getOtherPerson(name);
                if (amount > 0 && balances.containsKey(otherPerson) && balances.get(otherPerson) == -amount) {
                    System.out.println(otherPerson + " owes " + name + " $" + formattedAmount);
                    hasDebts = true;
                } else if (amount < 0 && balances.containsKey(otherPerson) && balances.get(otherPerson) == -amount) {
                    // Skip, as the other person already owes this person
                } else {
                    System.out.println(name + " owes " + otherPerson + " $" + formattedAmount);
                    hasDebts = true;
                }
            }
        }

        if (!hasDebts) {
            System.out.println("No debts, Huat Ah!");
        }
    }

    /**
     * Gets the other person involved in a transaction.
     *
     * @param name The name of the person.
     * @return The name of the other person.
     */
    private String getOtherPerson(String name) {
        return transactions.getOtherPerson(name);
    }

    /**
     * Deletes a debt transaction by index.
     *
     * @param index The index of the transaction to delete.
     */
    public void deleteDebt(int index) {
        transactions.remove(index);
    }

    /**
     * Finds debts involving a specific person.
     *
     * @param person The name of the person to find debts for.
     */
    public void findDebts(String person) {
        for (Transaction transaction : transactions.getTransactions()) {
            if (transaction.getInvolves(person)) {
                System.out.println(transaction);
            }
        }
    }

    /**
     * Clears all debts.
     */
    public void clearAllDebts() {
        transactions.clear();
    }

    /**
     * Gets or creates a member with the given name.
     *
     * @param name The name of the member.
     * @return The Member object.
     */
    private Member getOrCreateMember(String name) {
        return members.computeIfAbsent(name, Member::new);
    }

    /**
     * The main method to run the LongAh application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to LongAh!");
        LongAh app = new LongAh();
        while (true) {
            System.out.println("Enter command:");
            if (!app.scanner.hasNextLine()) {
                return;
            }
            String command = app.scanner.nextLine();
            String[] parts = command.split(" ");
            switch (parts[0]) {
            case "add":
                if (parts.length == 4 && parts[1].startsWith("p/")
                    && parts[2].startsWith("a/") && parts[3].startsWith("p/")) {
                    String person1 = parts[1].substring(2);
                    double amount = Double.parseDouble(parts[2].substring(2));
                    String person2 = parts[3].substring(2);
                    app.addDebt(person1, amount, person2);
                } else {
                    System.out.println("Invalid command format. Use 'add p/PERSON1 a/AMOUNT p/PERSON2'");
                }
                break;
            case "list":
                app.listAllDebts();
                break;
            case "delete":
                if (parts.length == 2) {
                    int index = Integer.parseInt(parts[1]);
                    app.deleteDebt(index);
                } else {
                    System.out.println("Invalid command format. Use 'delete INDEX'");
                }
                break;
            case "find":
                if (parts.length == 2) {
                    String person = parts[1];
                    app.findDebts(person);
                } else {
                    System.out.println("Invalid command format. Use 'find PERSON'");
                }
                break;
            case "clear":
                app.clearAllDebts();
                break;
            case "exit":
                return;
            default:
                System.out.println("Invalid command. Use 'add', 'list', 'delete', 'find', 'clear', or 'exit'.");
            }
        }
    }
}
