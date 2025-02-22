package treeExecution;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Objects.TypeObject;

public class CreateNodeTree {
    public static String pathJson = "archives/src/main/java/treeExecution/tree.json";
    public static int idObjectIO = 0;
    public static int idObjectTypeVariable = 0;

    public static void treeExecutionRoot(String typeOfRoot, JsonObject NodeDeclaration, String ObjectType) {
        try {
            //se carga el arbol de nodos
            JsonObject contentRoot = cargarJson();
            JsonArray root;
    
            if (contentRoot.has("root")) {
                root = contentRoot.getAsJsonArray("root");
            } else {
                root = new JsonArray();
            }
    
            // Clave que representa el ObjectType
            String objectTypeKey = String.format("ObjectType [%s]", ObjectType);
            JsonObject existingObjectType = null;
    
            // Verificamos si ya existe un ObjectType con la misma clave
            for (JsonElement element : root) {
                if (element.isJsonObject() && element.getAsJsonObject().has(objectTypeKey)) {
                    existingObjectType = element.getAsJsonObject();
                    break;
                }
            }
    
            if (existingObjectType != null) {
                // Si ya existe, simplemente agregamos el nuevo nodo a su lista
                JsonArray objectTypeList = existingObjectType.getAsJsonArray(objectTypeKey);
                JsonObject ObjectListNode = new JsonObject();
                ObjectListNode.add(typeOfRoot, NodeDeclaration);
                objectTypeList.add(ObjectListNode);
            } else {
                // Si no existe, creamos un nuevo ObjectType con su lista
                JsonObject newObjectType = new JsonObject();
                JsonArray objectTypeList = new JsonArray();
    
                JsonObject ObjectListNode = new JsonObject();
                ObjectListNode.add(typeOfRoot, NodeDeclaration);
                objectTypeList.add(ObjectListNode);
    
                newObjectType.add(objectTypeKey, objectTypeList);
                root.add(newObjectType);
            }
    
            // Guardamos el JSON modificado
            contentRoot.add("root", root);
            try (FileWriter fileWriter = new FileWriter(CreateNodeTree.pathJson)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(contentRoot, fileWriter);
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // arbol de ejecución para el output o para la funcion imprimir('');
    public static void createNodeOutput(String prompt, String object) {
        /*
         * {
         * "root": {
         * ****"NodeDeclaration": "__valueIO__",
         * ********"Declaration": {
         * ************"imprimir()": "__methods__",
         * ************"Salida": {
         * ****************"valor": "hola"
         * ******************}
         * ***************}
         * ***********}
         * *******}
         */

        // "NodeDeclarationOutput"
        try {
            JsonObject nodeDeclaration = new JsonObject();
            nodeDeclaration.addProperty(String.format("NodeDeclarationOutput [%s]: ", CreateNodeTree.idObjectIO),
                    object);

            JsonObject typeDeclaration = new JsonObject();
            typeDeclaration.addProperty(String.format("imprimir() [%s]: ", CreateNodeTree.idObjectIO),
                    "__methods__");

            JsonObject salida = new JsonObject();

            // definiendo las propiedades del json
            salida.addProperty(String.format("Valor [%s]: ", CreateNodeTree.idObjectIO), prompt);
            typeDeclaration.add(String.format("Salida [%s]: ", CreateNodeTree.idObjectIO), salida);
            nodeDeclaration.add(String.format("DeclarationOutput [%s]: ", CreateNodeTree.idObjectIO),
                    typeDeclaration);

            // creamos el arbol de ejecucion para el output gracias al root
            treeExecutionRoot(String.format("OutputRoot [%s]: ", CreateNodeTree.idObjectIO), nodeDeclaration,
                    TypeObject.objectIO());

            CreateNodeTree.idObjectIO += 1;

        } catch (Exception e) {

        }

    }

    // arbol de ejecucion para las variables!!
    public static void createNodeVariable(String object, String nameOfVariable, String identifier,
            String valueOfVariable) {

        /*
         * {
         **** "root": {
         ******** "NodeDeclaration": "__typeVariable__",
         ******** "Declaration": {
         ************ "Name Of Variable": "nombre",
         ************ "Identifier": "Cadena",
         ************ "Value": "bryan"
         ************* }
         ********* }
         ****** }
         */
        try {
            JsonObject nodeDeclaration = new JsonObject();
            nodeDeclaration.addProperty(
                    String.format("NodeDeclarationOutput [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    object);

            JsonObject typeDeclaration = new JsonObject();
            typeDeclaration.addProperty(String.format("Name Of Variable [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    nameOfVariable);
            typeDeclaration.addProperty(String.format("Identifier [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    identifier);
            typeDeclaration.addProperty(String.format("Value [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    valueOfVariable);

            nodeDeclaration.add(String.format("Declaration Of Variable [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    typeDeclaration);

            // creamos el arbol de ejecucion para el output gracias al root
            treeExecutionRoot(String.format("VariableRoot [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    nodeDeclaration,
                    TypeObject.objectTypeVariables());

            CreateNodeTree.idObjectTypeVariable += 1;
        } catch (Exception e) {

        }
    }

    // sobrecarga de metodo!!!
    public static void createNodeVariable(String object, String nameOfVariable, String identifier,
            int valueOfVariable) {

        /*
         * {
         **** "root": {
         ******** "NodeDeclaration": "__typeVariable__",
         ******** "Declaration": {
         ************ "Name Of Variable": "nombre",
         ************ "Identifier": "Entero",
         ************ "Value": "bryan"
         ************* }
         ********* }
         ****** }
         */
        try {
            JsonObject nodeDeclaration = new JsonObject();
            nodeDeclaration.addProperty(
                    String.format("NodeDeclarationOutput [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    object);

            JsonObject typeDeclaration = new JsonObject();
            typeDeclaration.addProperty(String.format("Name Of Variable [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    nameOfVariable);
            typeDeclaration.addProperty(String.format("Identifier [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    identifier);
            typeDeclaration.addProperty(String.format("Value [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    valueOfVariable);

            nodeDeclaration.add(String.format("Declaration Of Variable [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    typeDeclaration);

            // creamos el arbol de ejecucion para el output gracias al root
            treeExecutionRoot(String.format("VariableRoot [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    nodeDeclaration,
                    TypeObject.objectTypeVariables());
            CreateNodeTree.idObjectTypeVariable += 1;
        } catch (Exception e) {

        }
    }

    // sobrecarga de metodo!!!
    public static void createNodeVariable(String object, String nameOfVariable, String identifier,
            float valueOfVariable) {

        /*
         * {
         **** "root": {
         ******** "NodeDeclaration": "__typeVariable__",
         ******** "Declaration": {
         ************ "Name Of Variable": "nombre",
         ************ "Identifier": "Float",
         ************ "Value": "bryan"
         ************* }
         ********* }
         ****** }
         */
        try {
            JsonObject nodeDeclaration = new JsonObject();
            nodeDeclaration.addProperty(
                    String.format("NodeDeclarationOutput [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    object);

            JsonObject typeDeclaration = new JsonObject();
            typeDeclaration.addProperty(String.format("Name Of Variable [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    nameOfVariable);
            typeDeclaration.addProperty(String.format("Identifier [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    identifier);
            typeDeclaration.addProperty(String.format("Value [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    valueOfVariable);

            nodeDeclaration.add(String.format("Declaration Of Variable [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    typeDeclaration);

            // creamos el arbol de ejecucion para el output gracias al root
            treeExecutionRoot(String.format("VariableRoot [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    nodeDeclaration,
                    TypeObject.objectTypeVariables());
            CreateNodeTree.idObjectTypeVariable += 1;
        } catch (Exception e) {

        }
    }

    // sobrecarga de metodo!!!
    public static void createNodeVariable(String object, String nameOfVariable, String identifier,
            boolean valueOfVariable) {

        /*
         * {
         **** "root": {
         ******** "NodeDeclaration": "__typeVariable__",
         ******** "Declaration": {
         ************ "Name Of Variable": "nombre",
         ************ "Identifier": "Booleano",
         ************ "Value": "bryan"
         ************* }
         ********* }
         ****** }
         */
        try {
            JsonObject nodeDeclaration = new JsonObject();
            nodeDeclaration.addProperty(
                    String.format("NodeDeclarationOutput [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    object);

            JsonObject typeDeclaration = new JsonObject();
            typeDeclaration.addProperty(String.format("Name Of Variable [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    nameOfVariable);
            typeDeclaration.addProperty(String.format("Identifier [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    identifier);
            typeDeclaration.addProperty(String.format("Value [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    valueOfVariable);

            nodeDeclaration.add(String.format("Declaration Of Variable [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    typeDeclaration);

            // creamos el arbol de ejecucion para el output gracias al root
            treeExecutionRoot(String.format("VariableRoot [%s]: ", CreateNodeTree.idObjectTypeVariable),
                    nodeDeclaration,
                    TypeObject.objectTypeVariables());
            CreateNodeTree.idObjectTypeVariable += 1;
        } catch (Exception e) {

        }
    }

    // este metodo funciona para ver el estado actual del json para no sobreescribir
    // el fichero!!
    public static JsonObject cargarJson() {
        try (FileReader reader = new FileReader(CreateNodeTree.pathJson)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject()) {
                return jsonElement.getAsJsonObject();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("El archivo no existe o está vacío, creando uno nuevo.");
        }
        return new JsonObject(); // Si no existe el archivo, retornar un objeto vacío
    }
}
