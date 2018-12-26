package de.tasks.rmi.central;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {

    /**
     * 
     */
    private static final long serialVersionUID = 3030621567194917394L;

    private final Data data;

    public ServiceImpl() throws RemoteException {
        data = new DataImpl();
    }

    @Override
    public Data open() throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Data get() throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Data close() throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isOpen() throws RemoteException {
        // TODO Auto-generated method stub
        return false;
    }

}
