package seedu.duke;


import java.util.ArrayList;

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
    Expense(String payerName, float totalAmount, String[] payeeList){
        payerName = removeWhitespaces(payerName);
        for(int i = 1; i < payeeList.length; i++){
            payees.add(removeWhitespaces(payeeList[i]));
        }
        this.payerName = payerName;
        this.totalAmount = totalAmount;

        System.out.printf("Added new expense %.2f owed to %s by:",this.totalAmount,this.payerName);
        for(String payee : payees){
            System.out.print(payee + ", ");
        }
        System.out.println();
    }


    private String removeWhitespaces(String item){
        String itemWithoutWhitespaces = item.replaceAll("\\s+", " ").trim();
        return itemWithoutWhitespaces;
    }
    public String getPayerName() {
        return payerName;
    }

    /**
     *
     * @return : float showing the total amount before division
     */
    public float getTotalAmount() {
        return totalAmount;
    }

    public ArrayList<String> getPayees() {
        return payees;
    }
}
