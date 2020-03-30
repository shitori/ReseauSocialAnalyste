import java.rmi.Naming;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost/RMIDemo";
        DataRMI d = (DataRMI) Naming.lookup(url);
        System.out.println("Connected...");
        //String serverReply = Arrays.toString(d.getBest3());
    }
}
