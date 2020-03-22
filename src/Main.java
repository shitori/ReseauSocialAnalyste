import java.io.*;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        int i = 0;
        Data d = new Data();
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
