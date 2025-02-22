package Variables;

import Warnings.exeptions;
import treeExecution.CreateNodeTree;

//reglas de sintaxis para los diferentes tipos de datos del lenguaje
public class TypeVariables {
    // validaciones generales del lenguaje!!
    public static boolean validationsTypeVariables(String object, String nameOfVariable, String identifier,
            String valueOfVariable) {

        /*
         * Reglas de sintaxis
         * 
         * 1. no puede ser un numero el nombre de la variable
         * 
         * 2. no puede haber comillas ni en el nombre de la variable (<nombreVariable>)
         * ni en el tipo de dato (<TypeVariable>)
         * 
         * 3. obligatoriamente en el tipo de dato cadena debe estar en
         * valor(<__value__>) entre comillas
         * 
         */

        try {
            // validaciones normales de cualquier tipo de dato!!!
            if (nameOfVariable.isEmpty()) {
                throw new exeptions.EmptyNameVariableExeption();
            }
            if (identifier.isEmpty()) {
                throw new exeptions.EmptyIdentifierVariableExeption();
            }

            if (nameOfVariable.contains("\"\"") || nameOfVariable.contains("\"")) {
                throw new exeptions.NameOfVariableExeptionError();
            }
            if (identifier.contains("\"\"") || identifier.contains("\"")) {
                throw new exeptions.IdentifierOfVariableExeptionError();
            }
            if (nameOfVariable.matches("\\d+")) {
                throw new exeptions.NameOfVariableExeptionError();
            }
            if (identifier.matches("\\d+")) {
                throw new exeptions.IdentifierOfVariableExeptionError();
            }

            return true;

        } catch (exeptions.EmptyNameVariableExeption | exeptions.EmptyIdentifierVariableExeption
                | exeptions.NameOfVariableExeptionError | exeptions.IdentifierOfVariableExeptionError e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // sobrecarga de metodos
    public static boolean validationsTypeBoolean(String object, String nameOfVariable, String identifier,
            String valueOfVariable) {

        try {
            // validaciones normales de cualquier tipo de dato!!!
            if (nameOfVariable.isEmpty()) {
                throw new exeptions.EmptyNameVariableExeption();
            }
            if (identifier.isEmpty()) {
                throw new exeptions.EmptyIdentifierVariableExeption();
            }

            if (nameOfVariable.contains("\"\"") || nameOfVariable.contains("\"")) {
                throw new exeptions.NameOfVariableExeptionError();
            }
            if (identifier.contains("\"\"") || identifier.contains("\"")) {
                throw new exeptions.IdentifierOfVariableExeptionError();
            }
            if (nameOfVariable.matches("\\d+")) {
                throw new exeptions.NameOfVariableExeptionError();
            }
            if (identifier.matches("\\d+")) {
                throw new exeptions.IdentifierOfVariableExeptionError();
            }
            if (!"true".equalsIgnoreCase(valueOfVariable) && !"false".equalsIgnoreCase(valueOfVariable)) {
                throw new exeptions.BooleanError();
            }

            return true;

        } catch (exeptions.EmptyNameVariableExeption | exeptions.EmptyIdentifierVariableExeption
                | exeptions.NameOfVariableExeptionError | exeptions.IdentifierOfVariableExeptionError
                | exeptions.BooleanError e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void entero(String object, String nameOfVariable, String identifier, int valueOfVariable) {
        try {
            if (validationsTypeVariables(object, nameOfVariable, identifier, Integer.toString(valueOfVariable))) {
                // seguimos haciendo mas validaciones pero mas enfocado a entero

                if (identifier.equals("Entero")) {
                    CreateNodeTree.createNodeVariable(object, nameOfVariable, identifier, valueOfVariable);
                } else {
                    throw new exeptions.NotValueForIntegerError();
                }

            }
        } catch (NumberFormatException | exeptions.NotValueForIntegerError e) {
            System.out.println(e.getMessage());
        }

    }

    public static void flotante(String object, String nameOfVariable, String identifier, float valueOfVariable) {
        try {
            if (validationsTypeVariables(object, nameOfVariable, identifier, Float.toString(valueOfVariable))) {
                // seguimos haciendo mas validaciones pero mas enfocado a entero

                if (identifier.equals("Flotante")) {
                    CreateNodeTree.createNodeVariable(object, nameOfVariable, identifier, valueOfVariable);
                } else {
                    throw new exeptions.NotValueForIntegerError();
                }

            }
        } catch (NumberFormatException | exeptions.NotValueForIntegerError e) {
            System.out.println(e.getMessage());
        }

    }

    // esta listo!
    public static void cadena(String object, String nameOfVariable, String identifier, String valueOfVariable) {
        try {
            if (validationsTypeVariables(object, nameOfVariable, identifier, valueOfVariable)) {
                CreateNodeTree.createNodeVariable(object, nameOfVariable, identifier, valueOfVariable);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void booleano(String object, String nameOfVariable, String identifier, String valueOfVariable) {
        try {
            if (validationsTypeBoolean(object, nameOfVariable, identifier, valueOfVariable)) {
                // seguimos haciendo mas validaciones pero mas enfocado a entero

                if (identifier.equals("Booleano")) {
                    CreateNodeTree.createNodeVariable(object, nameOfVariable, identifier, Boolean.parseBoolean(valueOfVariable));
                } else {
                    throw new exeptions.NotValueForIntegerError();
                }

            }
        } catch (NumberFormatException | exeptions.NotValueForIntegerError e) {
            System.out.println(e.getMessage());
        }
    }
}
