package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Mariya on 26.03.2019.
 */
public interface Subtraction extends Remote{
    int Sub(int firstSubtraction, int secondSubtraction) throws RemoteException;
}
