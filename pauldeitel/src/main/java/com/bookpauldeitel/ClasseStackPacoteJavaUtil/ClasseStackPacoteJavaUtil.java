package com.bookpauldeitel.ClasseStackPacoteJavaUtil;

import java.util.EmptyStackException;
import java.util.Stack;

public class ClasseStackPacoteJavaUtil {

    public static void main(String[] args) {

        Stack<Number> stack = new Stack<Number>();

        // Usa o método push - adiciona numeros inteiros ao objeto stack
        stack.push(12L); // Adiciona um long ao objeto stack
        System.out.println("Pushed 12L");
        printStack(stack);
        stack.push(34567); // Adiciona um inteiro ao objeto stack
        System.out.println("Pushed 34567");
        printStack(stack);
        stack.push(1.0F); // Adiciona um float ao objeto stack
        System.out.println("Pushed 1.0F");
        printStack(stack);
        stack.push(1234.5678); // Adiciona um double ao objeto stack
        System.out.println("Pushed 1234.5678");
        printStack(stack);
        System.out.println("Pushed 1.0F");

        try {

            Number removedObject = null;

            // remove itens da stack
            while (true) {
                removedObject = stack.pop(); // Usa o método pop
                System.out.printf("Popped %s%n", removedObject);
                printStack(stack);
            }
        } catch (EmptyStackException emptyStackException) {
            emptyStackException.printStackTrace();
        }

    }

    private static void printStack(Stack<Number> stack) {
        if (stack.isEmpty()) {
            System.out.printf("stack is empty%n%n");
        } else {
            System.out.printf("stack contains: %s (top)%n", stack);
        }
    }

}
