package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

public class ReversePolishNotationService {

    final String[] operators = {"+","-","/","*"};

    protected Stack<String> operationsStack = new Stack<>();
    protected Stack<String> resultStack = new Stack<>();

    ReversePolishNotationService(){
        clearStacks();
    }

    public String Convert(String expression){
        ListIterator<String> tokens = getTokens(expression).listIterator();

        while (tokens.hasNext()) {
            String token = tokens.next();
            if (isNumber(token)) {
                resultStack.push(token);
            } else if (isOperator(token)) {
                while (!operationsStack.empty()
                        && isOperator(operationsStack.lastElement())
                        && (getPriority(token) <= getPriority(operationsStack.lastElement()))) {
                    resultStack.push(operationsStack.pop());
                }
                operationsStack.push(token);
            } else if (isLeftBracket(token)) {
                operationsStack.push(token);
            } else if (isRightBracket(token)) {
                while (!operationsStack.empty()
                        && !isLeftBracket(operationsStack.lastElement())) {
                    resultStack.push(operationsStack.pop());
                }
                operationsStack.pop();
            } else {
                System.out.println("Unexpected item!");
                clearStacks();
                return null;
            }
        }

        while (!operationsStack.empty()) {
            resultStack.push(operationsStack.pop());
        }

        return String.join(",", resultStack);
    }

    protected boolean isOperator(String token) {
        for (String op : operators)
            if (op.equals(token))
                return true;
        return false;
    }

    protected boolean isLeftBracket(String token) {
        return token.equals("(");
    }

    protected boolean isRightBracket(String token) {
        return token.equals(")");
    }

    protected int getPriority(String operation) {
        if (operation.equals("+") || operation.equals("-"))
            return 1;
        else
            return 2;
    }

    protected boolean isNumber (String token) {
        try {
            Integer.parseInt(token);
        }
        catch (Exception exc) {
            return false;
        }
        return true;
    }

    protected ArrayList<String> getTokens(String expression){
        ArrayList<String> tokens = new ArrayList<>();
        int i = 0;

        while (i < expression.length()){
            StringBuilder possibleToken = new StringBuilder(Character.toString(expression.charAt(i)));
            if (!isNumber(possibleToken.toString())){
                tokens.add(possibleToken.toString());
                i++;
            }
            else {
                i++;
                while (i < expression.length()
                        && isNumber(Character.toString(expression.charAt(i)))){
                    possibleToken.append(expression.charAt(i));
                    i++;
                }
                tokens.add(possibleToken.toString());
            }
        }

        return tokens;
    }

    private void clearStacks(){
        operationsStack.clear();
        resultStack.clear();
    }
}
