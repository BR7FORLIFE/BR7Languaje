package treeExecution;

import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class ClearNodeTree {

    public static void clearNodeTree() {
        JsonObject emptyJson = new JsonObject();
        try (FileWriter fileWriter = new FileWriter(CreateNodeTree.pathJson)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(emptyJson, fileWriter);
            CreateNodeTree.idObjectIO = 0;
            CreateNodeTree.idObjectTypeVariable = 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
