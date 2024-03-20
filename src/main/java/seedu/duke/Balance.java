package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Balance {
    protected String userName;
    protected Map<String, Float> balanceList;

    public Balance(String userName, Map<String, Float> userList) {
        this.userName = userName;
        this.balanceList = userList;
    }

    public Balance(String userName, ArrayList<Expense> expenses, ArrayList<User> users) {
        this.userName = userName;
        this.balanceList = new HashMap<>();

        // Populate balanceList with other Users from Group
        for (User user : users){
            if(!user.getName().equals(userName)){
                balanceList.put(user.getName(), 0f);
            }
        }

        // Add Expenses to balanceList
        for (Expense expense : expenses){
            addExpense(expense);
        }
    }

    public String getUserName() {
        return userName;
    }

    public Map<String, Float> getBalanceList() {
        return balanceList;
    }

    private void addExpense(Expense expense) {
        ArrayList<String> payees = expense.getPayees();
        int numberOfUsers = payees.size() + 1;
        Float amountPerUser = expense.getTotalAmount() / numberOfUsers;

        if(expense.getPayerName().equals(userName)){
            for(String payee : payees){
                Float currentOwed = balanceList.get(payee);
                Float newOwed = currentOwed + amountPerUser;

                balanceList.put(payee, newOwed);
            }
        } else if (expense.getPayees().contains(userName)) {
            String payerName = expense.getPayerName();
            Float currentOwed = balanceList.get(payerName);
            Float newOwed = currentOwed - amountPerUser;

            balanceList.put(payerName, newOwed);
        }
    }

    public void printBalance() {
        String firstLine = String.format("User %s's Balance List:", userName);
        System.out.println(firstLine);

        for (Map.Entry<String, Float> entry : balanceList.entrySet()) {
            String balanceLine = String.format("  %s : %.2f", entry.getKey(), entry.getValue());
            System.out.println(balanceLine);
        }

        System.out.println("End of Balance List");
    }
}
