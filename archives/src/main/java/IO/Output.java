package IO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Expresions.Expressions;
import Objects.TypeObject;
import Warnings.exeptions;
// clase output o de salida de datos
import treeExecution.CreateNodeTree;

/*
 * Nodo: Declaracion --> IO
 * |
 * |------imprimir() --> <__value__>  __value__
 *           |
 *           |--- <valor de la salida> __valueIO__
 * 
 */

public class Output {
    // atributties

    // getter and setters

    public static String getOutput(String prompt) {
        return prompt;
    }

    public static void ValidatePrompt(String prompt) { // validamos la sintaxis de imprimir('');
        try {
            if (prompt.startsWith("imprimir('") && prompt.endsWith("');")) {
                System.out.println(printValue(prompt));
                CreateNodeTree.createNodeOutput(printValue(prompt), TypeObject.objectIO());
                return;
            }

            if (prompt.startsWith("imprimir(") && prompt.contains("+") && prompt.endsWith(");")) {
                Expressions.validatePlusOperator(prompt);
                return;
            }

            if (prompt.startsWith("imprimir(") && prompt.endsWith(");")) {
                Output.printVariable(prompt);
                return;
            }

            else {
                throw new exeptions.SintaxErrorException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String printValue(String prompt) {
        /**
         * @param imprimir('hola que tal'); // sintaxis esperada
         *
         *                       comprobar si empieza con imprimir(' y finaliza con ')
         *                       hallar los indices de la primera y segunda comilla
         *                       simple (')
         *                       retornar el string entre dicho rango!
         *                       Problemas
         * 
         *                       1. el poder imprimir con comillas dobles []
         * 
         * 
         * 
         *                       ToDo
         * 
         *                       [] crear el codigo para imprimir las variables gracias
         *                       a la función!
         * 
         */
        try {
            String parsingPrompt = prompt.trim();

            int indexSingleQuoteStart = parsingPrompt.indexOf("'");
            int indexSingleQuoteFinished = parsingPrompt.indexOf("'", indexSingleQuoteStart + 1);

            // validaciones
            if (indexSingleQuoteStart == -1) {
                throw new exeptions.IndexNotValidException();
            }
            if (indexSingleQuoteFinished == -1) {
                throw new exeptions.IndexNotValidException();
            }

            String expectedString = parsingPrompt.substring(indexSingleQuoteStart + 1, indexSingleQuoteFinished);
            getOutput(expectedString);

            return expectedString;

        } catch (exeptions.IndexNotValidException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static void printVariable(String prompt) {
        /**
         * metodo para hacer calculos e imprimir variables en el lenguaje ya
         * definidos!!!
         * 
         * Problemas
         * 
         * 1. si el nodo de la variable esta en una posicion diferente de 0 dara un
         * error null
         * funciona cuando creas primero la variable y despues lo imprime pero si hay un
         * nodo antes dara null
         * 
         */

        try {
            String parsingPrompt = prompt.trim();

            int indexSingleQuoteStart = parsingPrompt.indexOf("(");
            int indexSingleQuoteFinished = parsingPrompt.indexOf(")", indexSingleQuoteStart + 1);

            // validaciones
            if (indexSingleQuoteStart == -1) {
                throw new exeptions.IndexNotValidException();
            }
            if (indexSingleQuoteFinished == -1) {
                throw new exeptions.IndexNotValidException();
            }

            String expectedString = parsingPrompt.substring(indexSingleQuoteStart + 1, indexSingleQuoteFinished);

            getOutput(expectedString);

            JsonObject currentJson = CreateNodeTree.cargarJson();

            if (currentJson.has("root") && currentJson.get("root") != null && currentJson.get("root").isJsonArray()) {

                JsonArray root = currentJson.get("root").getAsJsonArray();

                for (int index = 0; index < root.size(); index++) {
                    JsonObject objectType = root.get(index).getAsJsonObject();

                    if (objectType.has("ObjectType [__typeVariable__]")) {
                        JsonArray objectTypeList = objectType.get("ObjectType [__typeVariable__]").getAsJsonArray();

                        for (int indexVariableRoot = 0; indexVariableRoot < objectTypeList
                                .size(); indexVariableRoot++) {
                            JsonObject objectVariableRootObject = objectTypeList.get(indexVariableRoot)
                                    .getAsJsonObject();

                            JsonObject variableRootObject = objectVariableRootObject
                                    .getAsJsonObject(String.format("VariableRoot [%s]: ", indexVariableRoot));

                            JsonObject declarationOfVariableObject = variableRootObject.getAsJsonObject(
                                    String.format("Declaration Of Variable [%s]: ", indexVariableRoot));

                            String StringName = declarationOfVariableObject
                                    .get(String.format("Name Of Variable [%s]: ", indexVariableRoot)).getAsString();

                            if (StringName.equals(expectedString)) {
                                String valueOfVariable = declarationOfVariableObject
                                        .get(String.format("Value [%s]: ", indexVariableRoot)).getAsString();

                                System.out.println(valueOfVariable);
                                return;
                            }

                        }
                    }

                }

            } else {
                throw new exeptions.JsonNullError();
            }

        } catch (exeptions.IndexNotValidException | exeptions.JsonNullError e) {
            System.out.println(e.getMessage());
        }
    }
}
