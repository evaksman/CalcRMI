package com.company;

import java.rmi.registry.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculationServiceClient {
    private static boolean checkBrackets(String exp){
        int counter = 0;
        boolean check = true;

        for (int i = 0; i < exp.length() && check; i++) {
            if (exp.charAt(i) == '(') counter++;
            else if (exp.charAt(i) == ')') {
                counter--;
                if (counter < 0) check = false;
            }
        }
        if (counter != 0) check = false;

        return check;
    }

    private static boolean checkExpression(String exp){
        String regex = "([^0-9+\\-*/()])|([+\\-*/]{2,})|([+\\-*/]\\))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("(" + exp + ")");
        if (exp.isEmpty() || matcher.find())
            return false;
        else
            return checkBrackets(exp);
    }

    public static void main(String... args) throws Exception {
        System.out.print("Starting registry client...");
        Registry registry = LocateRegistry.getRegistry("localhost", 2099);
        RemoteCalculationService service = (RemoteCalculationService) registry.lookup("sample/CalcService");
        System.out.println("OK");

        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("Enter expression: ");
            String exp = scanner.nextLine();
            if (checkExpression(exp))
                System.out.println("Result: " + service.Calculation(exp));
            else
                System.out.println("Error!");
        }
    }
}