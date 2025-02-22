package treeExecution;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SearchNode {
    public static ArrayList<Integer> SearchIndexNode  = new ArrayList<>();

    public static void SearchIndexTypeVariable(String prompt){
        //busqueda del elemento por Key = VariableRoot [0]
        JsonObject tree = CreateNodeTree.cargarJson();
        System.out.println(tree);
        JsonArray root = tree.getAsJsonArray("root").getAsJsonArray();

        for(int i = 0; i < root.size(); i++){
            JsonObject variableRoot = root.get(i).getAsJsonObject();
            JsonObject variableRootList = variableRoot.get(String.format("VariableRoot [%s]", i)).getAsJsonObject();

            if(variableRootList.has(String.format("VariableRoot[%s]", i))){
                SearchIndexNode.add(i);
            }
        }
        System.out.println("searchIndexNode");
        for(int elements: SearchIndexNode){
            System.out.println(elements);
        }

    }
}
