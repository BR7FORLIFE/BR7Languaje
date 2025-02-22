package Variables;

import Objects.TypeObject;
import Warnings.exeptions;
import Warnings.exeptions.SintaxErrorException;

// clase para determinar la sintaxis de las variables y ademas crear un arbol de declaraciones

/*
 * Nodo: Declaracion
 * |
 * |----Nombre: <nombreVariable> __nombre__
 * |
 * |----Tipo: <tipoDato> __objeto<entero,flotante, cadena, booleano>__
 * |
 * |----valor: <valor> __valor__
 * 
 */
public class Variables {
    private static String variableName;
    private static String identifier;
    private static String value;

    public static void identifierTypeOfdata(String identifier) {
        try {
            switch (identifier) {
                case "Cadena":
                    if (Variables.value.startsWith("\"") && Variables.value.endsWith("\"")) {
                        int valueStart = Variables.value.indexOf("\"");
                        int valueFinished = Variables.value.indexOf("\"", valueStart + 1);

                        Variables.value = Variables.value.substring(valueStart + 1, valueFinished);

                        TypeVariables.cadena(TypeObject.objectTypeVariables(), Variables.variableName, Variables.identifier,
                                Variables.value);
                    } else {
                        throw new exeptions.SintaxErrorException();
                    }

                    break;

                case "Entero":
                    TypeVariables.entero(TypeObject.objectTypeVariables(), Variables.variableName, Variables.identifier,
                            Integer.parseInt(Variables.value));

                    break;

                case "Flotante":
                    TypeVariables.flotante(TypeObject.objectTypeVariables(), Variables.variableName, Variables.identifier,
                            Float.parseFloat(Variables.value));
                    break;

                case "Booleano":
                    TypeVariables.booleano(TypeObject.objectTypeVariables(), Variables.variableName, Variables.identifier,
                            Variables.value);
                    break;

                default:
                    throw new exeptions.IdentifierOfVariableExeptionError();
            }
        } catch (SintaxErrorException | exeptions.IdentifierOfVariableExeptionError e) {
            e.printStackTrace();
        }
    }

    public static void validateSintax(String sintaxString) {
        try {
            String parsingString = sintaxString.replace(" ", "");

            // indices para guardar los identificadores <nombre>: <tipo de dato> = <valor>;
            int startingIndex = 0;
            int indexNameOfVariables = parsingString.indexOf(":");
            int indexTypeOfVariables = parsingString.indexOf("=");
            int finalStringIndex = parsingString.length() - 1;

            // guardamos los valores gracias a los indices
            String nameOfVariables = parsingString.substring(startingIndex, indexNameOfVariables);
            String typeOfVariables = parsingString.substring(indexNameOfVariables + 1, indexTypeOfVariables);
            String valueVariable = parsingString.substring(indexTypeOfVariables + 1, finalStringIndex);

            if (parsingString.contains(":") && parsingString.endsWith(";") && parsingString.contains("=")) {
                // guardamos las variables en los atributos para seguir validando

                Variables.variableName = nameOfVariables.trim();
                Variables.identifier = typeOfVariables.trim();
                Variables.value = valueVariable.trim();
                identifierTypeOfdata(Variables.identifier);

            } else {
                System.out.println(
                        "<SintaxErrorException> Indica que se ha producido un error de sintaxis en el codigo, como la falta de un ; o un error de escritura de una función!");
            }

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}
