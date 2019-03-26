package com.company;

import java.rmi.registry.*;
import java.util.Scanner;

public class CalculationServiceClient {
    public static void main(String... args) throws Exception {
        System.out.print("Starting registry client...");
        Registry registry = LocateRegistry.getRegistry("localhost", 2100);
        RemoteCalculationService service = (RemoteCalculationService) registry.lookup("sample/CalcService");
        System.out.println("OK");

        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("Enter expression: ");
            String exp = scanner.nextLine();
            System.out.println("Result: " + service.Calculation(exp));
        }
    }
}