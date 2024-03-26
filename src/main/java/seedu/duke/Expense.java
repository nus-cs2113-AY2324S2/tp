package seedu.duke;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class to add a new expense
 */
public class Expense {
    private String payerName;
    private float totalAmount;
    private ArrayList<String> payees = new ArrayList<>();

    /**
     * Constructor to create new Expense
     * @param payerName : The name of the user who paid for the Expense
     * @param totalAmount : The total amount before being divided
     * @param payeeList : String array of people who owe the payer money
     *                  (Index 0 is the payer and will not be added to the payee list)
     */
    Expense(String payerName, float totalAmount, String[] payeeList) {
        payees.addAll(Arrays.asList(payeeList));
        this.payerName = payerName;
        this.totalAmount = totalAmount;

        System.out.printf("Added new expense %.2f paid by %s and split between:"
                ,this.totalAmount,this.payerName);
        for(String payee : payees) {
            System.out.print(payee + ", ");
        }
        System.out.println();
    }

    public String getPayerName() {
        return payerName;
    }

    /**
     * @return : float showing the total amount before division
     */
    public float getTotalAmount() {
        return totalAmount;
    }

    public ArrayList<String> getPayees() {
        return payees;
    }
}
