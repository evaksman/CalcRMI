package com.company;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

/**
 * Created by Mariya on 26.03.2019.
 */
public class SubtractionImpl implements Subtraction {
    public static final String BINDING_NAME = "sample/Subtraction";

    public int Sub (int firstSubtraction, int secondSubtraction){
        return firstSubtraction - secondSubtraction;
    }

    public static void main(String... args) throws Exception {
        System.out.print("Starting registry Subtraction...");
        final Registry registry = LocateRegistry.createRegistry(2102);
        System.out.println(" OK");

        final Subtraction service = new SubtractionImpl();
        Remote stub = UnicastRemoteObject.exportObject(service, 0);

        System.out.print("Binding service Subtraction...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
