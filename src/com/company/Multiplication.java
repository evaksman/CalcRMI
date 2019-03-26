package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Multiplication extends Remote {
    int Multi(int x, int y) throws RemoteException;
}
