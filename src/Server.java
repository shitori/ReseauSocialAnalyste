import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main (String[] args) throws Exception {
        Comment comment = new Comment();
        Message message = new Message();

        UnicastRemoteObject.exportObject(comment);
        UnicastRemoteObject.exportObject(message);

        Naming.rebind("/DistantComment", comment);
        Naming.rebind("/DistantMessage", message);

    }
}
