package financialtransactions;

import java.util.ArrayList;

public class TransactionList<T extends Transaction<?>> {
    private ArrayList<T> transactionList;

    public TransactionList(){
        this.transactionList = new ArrayList<>();
    }

    public int getTransactionListSize() {
        return this.transactionList.size();
    }

    public T getNthTransaction(int n) {
        return this.transactionList.get(n);
    }

    public Boolean addTransaction(T newTransaction){
        if(newTransaction != null){
            transactionList.add(newTransaction);
            return true;
        }
        return false;
    }

    public Boolean removeTransactionIndex (int index){
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

    public String toSave() {
        String baseString = "";
        for (T transaction : transactionList) {
            baseString += transaction.toSave();
        }
        return baseString;
    }
    

}
