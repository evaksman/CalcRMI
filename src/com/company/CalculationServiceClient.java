package com.company;

import java.rmi.registry.*;

public class CalculationServiceClient {

    public static void main(String... args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 2099);
        RemoteCalculationService service = (RemoteCalculationService) registry.lookup("sample/CalcService");
        // тут вызов service.Calculation(exp)
    }
}