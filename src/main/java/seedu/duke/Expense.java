package seedu.duke;


import java.util.ArrayList;

/**
 * A class to add a new expense
 */
public class Expense {
    private String payer_name;
    private float total_amount;

    private ArrayList<String> payees = new ArrayList<>();

    /**
     * Creates the Expense object corresponding to one expense.
     * @param payer_name : The person who paid for the expense
     * @param total_amount : The total amount before dividing between members of the group
     * @param payee_list : The list of people who owe money for this expense (The payer is included as index 0 and will not be added as a payee)
     */
    Expense(String payer_name, String total_amount, String[] payee_list){
        total_amount = removeWhitespaces(total_amount);
        payer_name = removeWhitespaces(payer_name);
        for(int i = 1; i < payee_list.length; i++){
            payees.add(removeWhitespaces(payee_list[i]));
        }
        this.payer_name = payer_name;
        this.total_amount = Float.parseFloat(total_amount);
        System.out.printf("Added new expense %.2f owed to %s by:",this.total_amount,this.payer_name);
        for(String payee : payees){
            System.out.print(payee + ", ");
        }
        System.out.println();
    }

    private String removeWhitespaces(String item){
        String itemWithoutWhitespaces = item.replaceAll("\\s+", " ").trim();
        return itemWithoutWhitespaces;
    }

}
