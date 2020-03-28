import java.rmi.Naming;

public class Server {
    public static void main(String[] args) throws Exception {
        Data d = new Data();
        Naming.rebind("RMIDemo", d);
        System.out.println("Server ready to use...");
    }
}
