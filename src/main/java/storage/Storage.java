package storage;

import financialtransactions.Inflow;
import financialtransactions.Outflow;
import financialtransactions.TransactionManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TransactionManager loadFile() {
        File f = new File(filePath + "/data.txt");
        TransactionManager manager = new TransactionManager();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] transactionInfo = sc.nextLine().split("\\|");
                double amount = Double.parseDouble(transactionInfo[1]);
                if (!transactionInfo[1].startsWith("-")) {
                    Inflow inflow = new Inflow(transactionInfo[0], amount, transactionInfo[2]);
                    inflow.setCategory(Inflow.Category.valueOf(transactionInfo[3]));
                    manager.addTransaction(inflow);
                } else {
                    Outflow outflow = new Outflow(transactionInfo[0], amount, transactionInfo[2]);
                    outflow.setCategory(Outflow.Category.valueOf(transactionInfo[3]));
                    manager.addTransaction(outflow);
                }
            }
        } catch (FileNotFoundException e) {
            createFileDir();
        }
        return manager;
    }

    private void createFileDir() {
        File f = new File(filePath);
        if (!f.mkdir()) {
            System.out.println("create file failed");
        }
    }

    public void saveFile(TransactionManager tm) {
        try {
            FileWriter fw = new FileWriter(filePath + "/data.txt");
            fw.write(tm.toSave());
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save tasks!");
        }
    }

}
