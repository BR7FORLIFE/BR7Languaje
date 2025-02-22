package treeExecution;

import IO.Output;
import Variables.Variables;

public class AST {

    // filtra el prompt por sintaxis
    public static void filterSintax(String sintaxString) {
        if (sintaxString.startsWith("imprimir")) {
            Output.ValidatePrompt(sintaxString);
        }
        if (sintaxString.contains(":")) {
            Variables.validateSintax(sintaxString);
        }
    }
}
