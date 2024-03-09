package financialtransactions;

import java.util.ArrayList;

public class TransactionList<T extends Transaction<?>> {
    private ArrayList<T> transactionList;

    public TransactionList(){
        this.transactionList = new ArrayList<>();
    }

    public Boolean addTransaction(T newTransaction){
        if(newTransaction != null){
            transactionList.add(newTransaction);
            return true;
        }
        return false;
    }

    public Boolean removeTransacitonIndex(int index){
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
            baseString += String.format("%d)\t%s\n", index, transaction.toString());
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
