package Expresions;

import java.util.List;

public class Expressions {
    // atributos

    public static void validatePlusOperator(String prompt) {
        String promptTrim = prompt.replace(" ", "");

        // contenido dentro de los parentesis al incluir la palabra clave '+'
        int startIndex = promptTrim.indexOf("(");
        int finallyIndex = promptTrim.indexOf(")", startIndex + 1);
        String content = promptTrim.substring(startIndex + 1, finallyIndex);
        System.out.println(content);
        // validamos el contenido entre el caracter especial '+'
        String[] contentVariables = content.splitWithDelimiters("\\+", content.length());
        integerOperatot(List.of(contentVariables));
    }

    public static void integerOperatot(List<String> args){
        for(String elements : args){
            System.out.println(elements);
        }
    }
}
