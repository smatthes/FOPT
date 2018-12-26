package de.tasks.rmi.central;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DataImpl extends UnicastRemoteObject implements Data {

    /**
     * 
     */
    private static final long serialVersionUID = 8608089399546311752L;

    private final ArrayList<String> data;

    public DataImpl() throws RemoteException {
        data = new ArrayList<>();
    }

    @Override
    public void append(String s) throws RemoteException {
        data.add(s);
    }

    @Override
    public ArrayList<String> getValues() throws RemoteException {
        return data;
    }

}
