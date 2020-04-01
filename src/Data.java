import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Data extends UnicastRemoteObject implements DataRMI {
    private ArrayList<Message> messages;
    private ArrayList<Comment> comments;
    private Best best3[];

    public Data() throws RemoteException {
        messages = new ArrayList<>();
        comments = new ArrayList<>();
        best3 = new Best[]{new Best(), new Best(), new Best()};
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getBest3() throws RemoteException {
        return Arrays.toString(best3);
    }

    public void setBest3(Best[] best3) {
        this.best3 = best3;
    }

    public void init() throws RemoteException {
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
                    messages.add(new Message(new Date(time), Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]), arrOfStr[2], arrOfStr[3]));
                } else if (arrOfStr.length == 5) {
                    comments.add(new Comment(new Date(time), Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]), arrOfStr[2], arrOfStr[3], Integer.parseInt(arrOfStr[4]), -1));
                } else if (arrOfStr.length == 6) {
                    comments.add(new Comment(new Date(time), Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]), arrOfStr[2], arrOfStr[3], -1, Integer.parseInt(arrOfStr[5])));
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

    public void searchBest3() throws RemoteException {
        best3 = new Best[]{new Best(), new Best(), new Best()};
        for (int k = 0; k < getMessages().size(); k++) {
            Message m = getMessages().get(k);
            for (int i = 0; i < best3.length; i++) {
                if (m.getScore() > best3[i].getScore()) {
                    boolean present = false;
                    for (int j = 0; j < best3.length; j++) {
                        if (best3[j].getId() == m.getIdMessage()) {
                            present = true;
                        }
                    }
                    if (!present) {
                        Best newb = new Best(m.getIdMessage(), m.getScore(), m.toString());
                        best3[i] = newb;
                    }
                }
            }
        }
        for (int k = 0; k < getComments().size(); k++) {
            Comment c = getComments().get(k);
            for (int i = 0; i < best3.length; i++) {
                if (c.getScore() > best3[i].getScore()) {
                    boolean present = false;
                    for (int j = 0; j < best3.length; j++) {
                        if (best3[j].getId() == c.getIdCommentaire()) {
                            present = true;
                        }
                    }
                    if (!present) {
                        Best newb = new Best(c.getIdCommentaire(), c.getScore(), c.toString());
                        best3[i] = newb;
                    }
                }
            }
        }
    }

    public void actualScore() throws RemoteException{
        Date now = new Date();
        System.out.println("Test à :" + now + "/" + now.getTime());
        for (int i = 0; i < getMessages().size(); i++) {
            Message m = actualScoreMessages(now, i);
            messages.set(i, m);
        }
    }

    public Message actualScoreMessages(Date now, int index) {
        Message m = getMessages().get(index);
        m.setScore(20);
        m.setScoreByDate(now);
        m.setFils(0);
        for (int i = 0; i < getComments().size(); i++) {
            if (getComments().get(i).getPidMessage() == m.getIdMessage()) {
                m.setFils(m.getFils() + 1);
                Comment c = actualScoreComments(now, i);
                comments.set(i, c);
                m.addScore(comments.get(i).getScore());
            }
        }
        if (m.getScore() < 0) {
            m.setScore(0);
        }
        return m;
    }

    public Comment actualScoreComments(Date now, int index) {
        Comment c = getComments().get(index);
        c.setScore(20);
        c.setScoreByDate(now);
        c.setFils(0);
        for (int i = 0; i < getComments().size(); i++) {
            if (getComments().get(i).getPidCommentaire() == c.getIdCommentaire()) {
                c.setFils(c.getFils() + 1);
                Comment cbis = actualScoreComments(now, i);
                comments.set(i, cbis);
                c.addScore(comments.get(i).getScore());
            }
        }
        if (c.getScore() < 0) {
            c.setScore(0);
        }
        if (c.getScore() < 0) {
            c.setScore(0);
        }
        return c;
    }

    public String printer() throws RemoteException {
        String s = "";
        for (int i = 0; i < getMessages().size(); i++) {
            s += printerMessage(i);
        }
        return s;
    }

    public String printerMessage(int index) {
        String s = "";
        Message m = messages.get(index);
        s += m.toString();
        for (int i = 0; i < getComments().size(); i++) {
            if (getComments().get(i).getPidMessage() == m.getIdMessage()) {
                s += printComment(i, 1);
            }
        }
        return s;
    }

    public String printComment(int index, int nbTab) {
        String s = "";
        for (int i=0;i<nbTab;i++){
            s+="\t";
        }
        Comment c = comments.get(index);
        s += c.toString();
        for (int i = 0; i < getComments().size(); i++) {
            if (getComments().get(i).getPidCommentaire() == c.getIdCommentaire()) {
                s += printComment(i, nbTab+1);
            }
        }
        return s;
    }

    @Override
    public String toString() {
        return "Data{" +
                "messages=" + messages +
                ", comments=" + comments +
                ", best3=" + Arrays.toString(best3) +
                '}';
    }
}
