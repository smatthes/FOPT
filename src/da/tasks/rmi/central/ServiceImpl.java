package da.tasks.rmi.central;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {

    /**
     * 
     */
    private static final long serialVersionUID = 3030621567194917394L;

    private final DataImpl dataImpl;
    
    private boolean isOpen;

    public ServiceImpl() throws RemoteException {
        dataImpl = new DataImpl();
        open();
    }

    @Override
    public synchronized Data open() throws RemoteException {
        if(!isOpen) {
            UnicastRemoteObject.exportObject(dataImpl, 0);
            isOpen = true;
        }
        
        return get();
    }

    @Override
    public synchronized Data get() throws RemoteException {
        return dataImpl;
    }

    @Override
    public synchronized Data close() throws RemoteException {
        if(isOpen) {            
            isOpen = !UnicastRemoteObject.unexportObject(dataImpl, true);        
        }
        
        return get();
    }

    @Override
    public synchronized boolean isOpen() throws RemoteException {
        return isOpen;
    }

}
