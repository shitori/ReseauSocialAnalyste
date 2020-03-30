import java.rmi.Naming;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws Exception {
        Data d = new Data();
        Naming.rebind("RMIDemo", d);
        System.out.println("Server ready to use...");

        d.init();
        while (true) {
            d.actualScore();
            d.searchBest3();
            System.out.println(Arrays.toString(d.getBest3()));
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
