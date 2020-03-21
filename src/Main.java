import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Date;

public class Main {

    public static void init() {
        BufferedReader reader;
        try {
            Date d = new Date();
            reader = new BufferedReader(new FileReader(
                    "reseauSocial.txt"));
            String line = reader.readLine();
            while (line != null) {
                long time = ((d.getTime() - 500000)) + (int) (Math.random() * ((d.getTime()) - (d.getTime() - 500000)));
                String[] arrOfStr = line.split("\\|");

                if (arrOfStr.length == 4) {
                    Message.addMessage(new Message(new Date(time), Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]), arrOfStr[2], arrOfStr[3]));
                } else if (arrOfStr.length == 5) {
                    Comment.addComment(new Comment(new Date(time), Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]), arrOfStr[2], arrOfStr[3], Integer.parseInt(arrOfStr[4]), -1));
                } else if (arrOfStr.length == 6) {
                    Comment.addComment(new Comment(new Date(time), Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]), arrOfStr[2], arrOfStr[3], -1, Integer.parseInt(arrOfStr[5])));
                } else {
                    System.err.println("ligne non reconnue");
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        init();
        int i = 0;
        while (true){
            Best.actualScore();
            Best.test();
            System.out.println(Arrays.toString(Best.getBest3()));
            String str = Best.printer();
            BufferedWriter writer = null;
            try {

                Thread.sleep(5000);
                writer = new BufferedWriter(new FileWriter("log"+i));
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
