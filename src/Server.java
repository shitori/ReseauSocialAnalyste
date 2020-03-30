import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Naming;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws Exception {
        Data d = new Data();
        Naming.rebind("RMIDemo", d);
        System.out.println("Server ready to use...");

        int i = 0;
        d.init();
        while (true) {
            d.actualScore();
            d.searchBest3();
            System.out.println(Arrays.toString(d.getBest3()));
            String str = d.printer();
            BufferedWriter writer = null;
            try {
                Thread.sleep(5000);
                writer = new BufferedWriter(new FileWriter("log" + i));
                writer.write(str);
                writer.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
