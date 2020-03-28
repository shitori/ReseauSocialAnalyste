import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataRMI extends Remote {
    void init() throws RemoteException;
    void actualScore() throws RemoteException;
    Best[] getBest3() throws RemoteException;
    void searchBest3() throws RemoteException;
    String printer() throws RemoteException;
}
