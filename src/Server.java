import java.rmi.Naming;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws Exception {
        Data d = new Data();
        Naming.rebind("DataRMI", d);
        System.out.println("Server ready to use...");
    }
}
