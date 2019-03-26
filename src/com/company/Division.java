package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by angel on 24.03.2019.
 */
public interface Division extends Remote{
    int Div(int firstDivident, int secondDivider) throws RemoteException;
}
