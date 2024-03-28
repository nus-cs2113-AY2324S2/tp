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

    public T getNthTransaction(int n) throws Exception{
        if(n >= this.transactionList.size()){
            throw new Exception("Invalid index");
        }
        return this.transactionList.get(n);
    }

    public int getIndexOfParticularTransaction(T particularTransaction) {
        return this.transactionList.indexOf(particularTransaction);
    }

    public boolean addTransaction(T newTransaction){
        if (newTransaction != null){
            transactionList.add(newTransaction);
            return true;
        }
        return false;
    }

    public boolean removeTransactionIndex (int index) throws Exception{
        transactionList.remove(index);
        return true;
    }

    public boolean editTransactionIndex (int index, T transaction) throws Exception {
        transactionList.set(index, transaction);
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
