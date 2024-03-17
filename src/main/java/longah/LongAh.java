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
     * Settles up the debts of the specified borrower by creating a transaction
     * to pay a single person in the group.
     * @param borrowerName The name of the borrower to settle up.
     */
    public void settleUp(String borrowerName) {
        ArrayList<Subtransaction> subtransactions = members.solveTransactions();
        String lenderName = "";
        double amountRepaid = 0.0;
        for (Subtransaction subtransaction : subtransactions) {
            if (subtransaction.getBorrower().isName(borrowerName)) {
                lenderName = subtransaction.getLender().getName();
                amountRepaid = subtransaction.getAmount();
                break;
            }
        }
        assert borrowerName != null : "input name is not found!";
        String transactionExpression = borrowerName + " p/" + lenderName + " a/" + amountRepaid;
        try {
            transactions.add(transactionExpression, members);
            System.out.println(borrowerName +  " has repaid " + lenderName + " $" + amountRepaid);
            System.out.println(borrowerName + " has no more debts!");
        } catch (LongAhException e) {
            LongAhException.printException(e);
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
                case "listdebts":
                    if (transactions.getTransactionListSize() == 0) {
                        System.out.println("No transactions found.");
                        break;
                    } else {
                        app.listAllDebts();
                        break;
                    }
                case "listtransactions":
                    System.out.println(transactions.listTransactions());
                    break;
                case "delete":
                    if (parts.length == 2) {
                        int index = Integer.parseInt(parts[1]) - 1;
                        transactions.remove(index);
                    } else {
                        System.out.println("Invalid command format. Use 'delete INDEX'");
                    }
                    break;
                case "findpayment":
                    if (parts.length == 2) {
                        String person = parts[1];
                        System.out.println(transactions.findTransactions(person));
                    } else {
                        System.out.println("Invalid command format. Use 'findPayment PERSON'");
                    }
                    break;
                case "finddebt":
                    if (parts.length == 2) {
                        String person = parts[1];
                        System.out.println(transactions.findDebts(person));
                    } else {
                        System.out.println("Invalid command format. Use 'findDebt PERSON'");
                    }
                    break;
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
                case "settleup":
                    if (parts.length == 2) {
                        String name = parts[1];
                        app.settleUp(name);
                    } else {
                        System.out.println("Invalid command format. Use 'settleup NAME'");
                    }
                    break;
                case "exit":
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid command. Use 'add', 'listdebts', 'listtransactions'," +
                            " 'delete', 'findpayment', 'finddebt', 'clear', or 'addmember'" +
                            ", 'exit'.");
                }
            } catch (LongAhException e) {
                LongAhException.printException(e);
            }
        }
    }
}
