import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class Client {
    public static void main (String[] args) throws Exception {
        Comment comment = (Comment) Naming.lookup("rmi://localhost/DistantComment");
        Message message = (Message) Naming.lookup("rmi://localhost/DistantMessage");

        // message.addMessage(m)
    }
}
