import java.rmi.Naming;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws Exception {
        Data d = new Data();
        Naming.rebind("DataRMI", d); // Rebind le nom à l'objet Data.
        System.out.println("Server ready to use...");
    }
}
