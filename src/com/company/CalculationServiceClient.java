package com.company;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculationServiceClient {
    private static RemoteCalculationService service;

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

    private static boolean checkExpression(String exp) {
        String regex = "([^0-9+*-/()])|([+*-/]{2,})|([+*-/]\\))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("(" + exp + ")");
        return !(exp.isEmpty() || matcher.find()) && checkBrackets(exp);
    }

    private static void registryService() throws RemoteException, NotBoundException {
        System.out.print("Starting registry client...");

        Registry registry = LocateRegistry.getRegistry("localhost", 2099);
        service = (RemoteCalculationService) registry.lookup("sample/CalcService");

        System.out.println("OK");
    }

    private static void scanExpression() throws RemoteException {
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

    public static void main(String... args) throws Exception {
        try {
            registryService();
            scanExpression();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}