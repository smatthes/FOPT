package de.tasks.rmi.central;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Data extends Remote {

    public void append(String s);

    public ArrayList<String> getValues();

}
