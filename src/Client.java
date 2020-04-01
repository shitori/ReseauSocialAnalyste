import java.rmi.Naming;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost/DataRMI";
        DataRMI d = (DataRMI) Naming.lookup(url);
        System.out.println("Connected...");

        d.init();
        while (true) {
            d.actualScore();
            d.searchBest3();
            System.out.println(d.getBest3());
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
