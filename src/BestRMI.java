import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BestRMI extends Remote {
    public void actualScore() throws RemoteException;
    public void test() throws RemoteException;
}
