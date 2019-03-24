package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteHelloService extends Remote {
    Object sayHello(String name) throws RemoteException;
}
