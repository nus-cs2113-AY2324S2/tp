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

    public boolean addTransaction(T newTransaction){
        if (newTransaction != null){
            transactionList.add(newTransaction);
            return true;
        }
        return false;
    }

    public boolean removeTransactionIndex (int index){
        printTransactionsSafeInfo();
        if (index >= transactionList.size() || index < 0){
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
        StringBuilder baseString = new StringBuilder("Transactions: \n");
        int index = 1;
        for(T transaction : transactionList){
            baseString.append(String.format("%d)  %s\n", index, transaction.toString()));
            index += 1;
        }
        return baseString.toString();
    }

    public String toSave() {
        StringBuilder baseString = new StringBuilder();
        for (T transaction : transactionList) {
            baseString.append(transaction.toSave());
        }
        return baseString.toString();
    }

    protected void printTransactionsSafeInfo() {
        int index = 1;
        for (T transaction : transactionList) {
            System.out.print(index++);
            System.out.println(" " + transaction.getName() + " " + transaction.getCategory());
        }
    }
}
