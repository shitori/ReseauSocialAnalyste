import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        while (true){
            Best.actualScore();
            Best.test();
            System.out.println(Arrays.toString(Best.getBest3()));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*Best.printer();
        Best.actualScore();
        Best.printer();*/

    }
}
