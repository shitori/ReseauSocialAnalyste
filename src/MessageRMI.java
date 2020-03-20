import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageRMI extends Remote {
    public void addMessage(Message m) throws RemoteException;
}
