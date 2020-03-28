import java.rmi.Naming;

public class Client {
    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost/RMIDemo";
        Data d = (Data) Naming.lookup(url);
        System.out.println("Connected...");
    }
}
