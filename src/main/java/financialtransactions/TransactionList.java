package financialtransactions;

import java.util.ArrayList;

public class TransactionList<T extends Transaction<?>> {
    private ArrayList<T> transactionList;

    public TransactionList(){
        this.transactionList = new ArrayList<>();
    }

    public ArrayList<T> getTransactionList() {
        return this.transactionList;
    }

    public boolean addTransaction(T newTransaction){
        if (newTransaction != null){
            transactionList.add(newTransaction);
            return true;
        }
        return false;
    }

    public Boolean removeTransactionIndex(int index){
        if(index >= transactionList.size() || index < 0){
            System.out.println("Invalid Index");
            return false;
        }
        transactionList.remove(index);
        return true;
    }

    public double getBalance(){
        double balance = 0.00;
        for(Transaction<?> transaction : transactionList){
            balance += transaction.getAmount();
        }
        return balance;
    }
    @Override
    public String toString(){
        String baseString = "Transactions: \n";
        Integer index = 1;
        for(T transaction : transactionList){
            baseString += String.format("%d)  %s\n", index, transaction.toString());
            index += 1;
        }
        return baseString;
    }

    public String lastNTransactions(int n) {
        String baseString = "Transactions: \n";
        int listSize = transactionList.size();
        int index = 1;
        for (int i = listSize - 1; i >= listSize - n - 1; i--) {
            T transaction = transactionList.get(i);
            baseString += String.format("%d)  %s\n", index, transaction.toString());
            index++;
        }
        return baseString;
    }
    
    public String toSave() {
        String baseString = "";
        for (T transaction : transactionList) {
            baseString += transaction.toSave();
        }
        return baseString;
    }
    
}
