package com.company;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class MultiplicationImpl implements Multiplication {

    public static final String BINDING_NAME = "sample/Multiplication";

    public int Multi(int x, int y)
    {
        return x*y;
    }

    public static void main(String... args) throws Exception {
        System.out.print("Starting registry multiplication...");
        final Registry registry = LocateRegistry.createRegistry(2100);
        System.out.println(" OK");

        final Multiplication service = new MultiplicationImpl();
        Remote stub = UnicastRemoteObject.exportObject(service, 0);

        System.out.print("Binding service multiplication...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }

}

