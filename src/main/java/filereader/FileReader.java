package filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private final String filePath;


    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<ArrayList<Character>> readEnemyDesign() throws IOException {
        File f = new File(filePath);
        System.out.println(filePath);
        if (f.exists()) {
            System.out.println("exist");
        } else {
            boolean fe = f.createNewFile();
            System.out.println("no exist");
        }
        Scanner in = new Scanner(f);
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        while (in.hasNextLine()) {
            ArrayList<Character> row = new ArrayList<>();
            String line = in.nextLine();
            for (int i = 0; i < line.length(); i += 1) {
                row.add(line.charAt(i));
            }
            map.add(row);
        }
        return map;
    }

}
