package filereader;

import command.ErrorCommand;

import java.io.*;
import java.util.ArrayList;

public class FileReader {
    private final String filePath;


    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<ArrayList<Character>> readEnemyDesign() throws IOException {
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found at: " + filePath);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            ArrayList<Character> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i += 1) {
                row.add(line.charAt(i));
            }
            map.add(row);
        }
        reader.close();
        inputStream.close();
        return map;
    }
}
