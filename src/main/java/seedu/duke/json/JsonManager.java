package seedu.duke.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonManager {

    InputStream inputStream;
    // Creating a new Gson instance
    Gson gson;
    InputStreamReader reader;
    List<JsonObject> jsonArray;

    String moduleDescription;

    int moduleMC;

    String moduleTitle;

    public JsonManager() {

        this.inputStream = this.getClass().getResourceAsStream("/moduleInfo.json");
        if (inputStream == null) {
            throw new RuntimeException("Cannot find resource file");
        }

        this.gson = new Gson();

        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            Type type = new TypeToken<List<JsonObject>>(){}.getType();
            jsonArray = gson.fromJson(reader, type);
            this.reader = reader;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean moduleExist(String moduleCode) {
        // Now, you can iterate through the array of objects just like before
        for (JsonObject obj : jsonArray) {
            // Process the object as needed; assuming there's a 'name' field
            String name = obj.get("moduleCode").getAsString();  // Replace 'name' with actual field names
            // If you want to match a specific module code, add an if check here
            if (name.equals(moduleCode)) {
                // Print out or process the module info
                return true;
            }
        }
        return false;
    }

    public void getModuleInfo(String moduleCode) {
        // Now, you can iterate through the array of objects just like before
        for (JsonObject obj : jsonArray) {
            // Process the object as needed; assuming there's a 'name' field
            String name = obj.get("moduleCode").getAsString();  // Replace 'name' with actual field names
            // If you want to match a specific module code, add an if check here
            if (name.equals(moduleCode)) {
                // Print out or process the module info
                this.moduleMC = obj.get("moduleCredit").getAsInt();
                this.moduleDescription = obj.get("description").getAsString();
                this.moduleTitle = obj.get("title").getAsString();
            }
        }
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public int getModuleMC() {
        return moduleMC;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }
}
