package seedu.duke.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonManager {

    public void getModuleInfo(String moduleCode) {

        InputStream inputStream = this.getClass().getResourceAsStream("/moduleInfo.json");
        if (inputStream == null) {
            throw new RuntimeException("Cannot find resource file");
            //System.out.println("Inputstream is null");
        }

        // Creating a new Gson instance
        Gson gson = new Gson();

        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            // Define the type of the data structure, e.g., a List of JsonObjects
            Type type = new TypeToken<List<JsonObject>>(){}.getType();
            List<JsonObject> jsonArray = gson.fromJson(reader, type);

            // Now, you can iterate through the array of objects just like before
            for (JsonObject obj : jsonArray) {
                // Process the object as needed; assuming there's a 'name' field
                String name = obj.get("moduleCode").getAsString();  // Replace 'name' with actual field names
                //System.out.println(name);
                // If you want to match a specific module code, add an if check here
                if (name.equals(moduleCode)) {
                    // Print out or process the module info
                    System.out.println("Found module: " + obj);
                }
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("testse");
        }
    }
}
