package longah;

import java.util.Scanner;
import java.util.ArrayList;

import longah.util.MemberList;
import longah.util.TransactionList;
import longah.util.Subtransaction;
import longah.exception.LongAhException;

/**
 * LongAh class manages debts between members.
 */
public class LongAh {
    private static MemberList members = new MemberList();
    private static TransactionList transactions = new TransactionList();
    private Scanner scanner;

    /**
     * Constructs a new LongAh instance.
     */
    public LongAh() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lists all debts between members.
     */
    public void listAllDebts() {
        ArrayList<Subtransaction> subtransactions = members.solveTransactions();

        System.out.println("Best Way to Solve Debts:");
        for (Subtransaction subtransaction : subtransactions) {
            System.out.println(subtransaction);
        }
    }

    /**
     * The main method to run the LongAh application.
     *
     * @param args
     *            The command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to LongAh!");
        LongAh app = new LongAh();
        while (true) {
            try {
                System.out.print("Enter command: ");
                if (!app.scanner.hasNextLine()) {
                    return;
                }
                String command = app.scanner.nextLine();
                String[] parts = command.split(" ", 2);
                switch (parts[0]) {
                case "add":
                    transactions.add(parts[1], members);
                    break;
                case "list":
                    app.listAllDebts();
                    break;
                case "delete":
                    if (parts.length == 2) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    transactions.remove(index);
                    } else {
                    System.out.println("Invalid command format. Use 'delete INDEX'");
                    }
                    break;
                case "findPayment":
                    if (parts.length == 2) {
                    String person = parts[1];
                    transactions.findPayments(person);
                    } else {
                    System.out.println("Invalid command format. Use 'findPayment PERSON'");
                    }
                    break;
                case "findDebt":
                    if (parts.length == 2) {
                        String person = parts[1];
                        transactions.findDebts(person);
                    } else {
                        System.out.println("Invalid command format. Use 'findDebt PERSON'");
                    }
                case "clear":
                    transactions.clear();
                    break;
                case "addmember":
                    if (parts.length == 2) {
                        String name = parts[1];
                        members.addMember(name);
                    } else {
                        System.out.println("Invalid command format. Use 'addmember NAME'");
                    }
                    break;
                case "listmembers":
                    members.listMembers();
                    break;
                case "exit":
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid command. Use 'add', 'list', 'delete', 'find', 'clear', or 'exit'.");
                }
            } catch (LongAhException e) {
                LongAhException.printException(e);
            }
        }
    }
}
