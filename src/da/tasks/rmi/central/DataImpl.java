package da.tasks.rmi.central;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class DataImpl implements Data, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8608089399546311752L;

    private final ArrayList<String> dataImpl;

    public DataImpl() throws RemoteException {
        dataImpl = new ArrayList<>();
    }

    @Override
    public synchronized void append(String s) throws RemoteException {
        dataImpl.add(s);
    }

    @Override
    public synchronized ArrayList<String> getValues() throws RemoteException {
        return dataImpl;
    }

}
