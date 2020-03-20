import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CommentRMI extends Remote {
    public void addComment(Comment c) throws RemoteException;
}
