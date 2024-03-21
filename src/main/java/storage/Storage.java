package storage;

import health.Health;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private Health health;

    public void save(int id, Health health) {
        assert health != null : "Health object cannot be null when saving";
        assert id >= 0 : "ID must be non-negative for saving";
        String fileName = "health_data_" + id + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            //bw.write("Height: " + Health.height);
            bw.newLine();
            //bw.write("Weight: " + Health.weight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Storage load(int id) {
        assert id >= 0 : "ID must be non-negative for loading";
        String fileName = "health_data_" + id + ".txt";
        Storage storage = new Storage();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storage;
    }

    public static void readFile(String fileName) {
        assert fileName != null && !fileName.isEmpty() : "File name cannot be null or empty";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHealth(Health health) {
        assert health != null : "Cannot set a null Health object";
        this.health = health;

    }

}

