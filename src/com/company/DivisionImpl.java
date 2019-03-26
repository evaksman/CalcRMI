package com.company;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

/**
 * Created by angel on 24.03.2019.
 */
public class DivisionImpl implements Division {
    public static final String BINDING_NAME = "sample/Division";

    public int Div(int firstDivident, int secondDivider){
        return firstDivident / secondDivider;
    }

    public static void main(String... args) throws Exception {
        System.out.print("Starting registry division...");
        final Registry registry = LocateRegistry.createRegistry(2100);
        System.out.println(" OK");

        final Division service = new DivisionImpl();
        Remote stub = UnicastRemoteObject.exportObject(service, 0);

        System.out.print("Binding service division...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
