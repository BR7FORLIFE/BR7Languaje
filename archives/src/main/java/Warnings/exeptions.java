package Warnings;

public class exeptions {
    //errores de sintaxis
    public static class IndexNotValidException extends Exception {
        public IndexNotValidException() {
            super("<IndexNotValidException!> indice no valido para el String!");
        }
    }

    public static class SintaxErrorException extends Exception {
        public SintaxErrorException() {
            super("<SintaxErrorException> Indica que se ha producido un error de sintaxis en el codigo, como la falta de un ; o un error de escritura de una función! ");
        }
    }

    //errores de tipos
    public static class NotValueForIntegerError extends Exception {
        public NotValueForIntegerError() {
            super("<NotValueForIntegerError> El valor de la variable que ha digitado no es entero!");
        }
    }

    public static class BooleanError extends Exception {
        public BooleanError() {
            super("<BooleanError> se esperaba un valor verdadero o falso! objeto de tipo booleano!");
        }
    }


    // variables error!
    public static class VariableError extends Exception {
        public VariableError() {
            super("<VariableError> Error en el valor de la variable!");
        }
    }

    public static class IdentifierOfVariableExeptionError extends Exception {
        public IdentifierOfVariableExeptionError() {
            super("<IdentifierOfVariableExeptionError> error en el tipo de dato de la variable! (Cadena, Flotante, Entero , Booleano)");
        }
    }

    public static class NameOfVariableExeptionError extends Exception {
        public NameOfVariableExeptionError() {
            super("<NameOfVariableExeptionError> error en el nombre de la variable!");
        }
    }

    public static class EmptyIdentifierVariableExeption extends Exception {
        public EmptyIdentifierVariableExeption() {
            super("<EmptyIdentifierVariableExeption> el tipo de dato esta vacio!");
        }
    }

    public static class EmptyNameVariableExeption extends Exception {
        public EmptyNameVariableExeption() {
            super("<EmptyNameVariableExeption> nombre de la variable esta vacia!");
        }
    }

    //errores de escritura!
    public static class JsonNullError extends Exception {
        public JsonNullError() {
            super("<JsonNullError> No se encuentra dicha variable en el nodo! o el TreeExecution es nulo!");
        }
    }
}
