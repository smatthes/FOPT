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
    public Data open() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Data get() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Data close() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isOpen() {
        // TODO Auto-generated method stub
        return false;
    }

}
