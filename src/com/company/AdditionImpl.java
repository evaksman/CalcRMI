package com.company;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

/**
 * Created by angel on 24.03.2019.
 */
public class AdditionImpl implements Addition {
    public static final String BINDING_NAME = "sample/Addition";

    public int Add(int firstAdded, int secondAdded){
        return firstAdded + secondAdded;
    }

    public static void main(String... args) throws Exception {
        System.out.print("Starting registry addition...");
        final Registry registry = LocateRegistry.createRegistry(2100);
        System.out.println(" OK");

        final Addition service = new AdditionImpl();
        Remote stub = UnicastRemoteObject.exportObject(service, 0);

        System.out.print("Binding service addition...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
