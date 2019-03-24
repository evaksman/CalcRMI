package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCalculationService extends Remote {
    int Calculation(String expression) throws RemoteException;
}
