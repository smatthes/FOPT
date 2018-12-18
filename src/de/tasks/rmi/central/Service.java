package de.tasks.rmi.central;

import java.rmi.Remote;

public interface Service extends Remote {

    public Data open();

    public Data get();

    public Data close();

    public boolean isOpen();

}
