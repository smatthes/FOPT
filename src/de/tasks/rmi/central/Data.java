package de.tasks.rmi.central;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Data extends Remote {

    public void append(String s) throws RemoteException;

    public ArrayList<String> getValues() throws RemoteException;

}
