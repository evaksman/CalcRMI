package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by angel on 24.03.2019.
 */
public interface Addition extends Remote{
    int Add(int firstAdded, int secondAdded) throws RemoteException;
}
