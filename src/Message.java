import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Message {
    private Date date;
    private int idMessage;
    private int idUser;
    private String message;
    private String user;
    private int score;
    private int fils;

    private long personalScore;

    public long getPersonalScore() {
        return personalScore;
    }

    public void setPersonalScore(long personalScore) {
        this.personalScore = personalScore;
    }


    public int getFils() {
        return fils;
    }

    public void setFils(int fils) {
        this.fils = fils;
    }

    private static ArrayList<Message> messages = new ArrayList<>();

    public static ArrayList<Message> getMessages() {
        return messages;
    }

    public static void setMessages(ArrayList<Message> messages) {
        Message.messages = messages;
    }

    public Message(Date d, int im, int iu, String m, String u) {
        date = d;
        idMessage = im;
        idUser = iu;
        message = m;
        user = u;
        score = 20;
        fils = 0;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String toString() {
        return "[" + fils + " fils/" + score + " score/" + personalScore + " score perso] De " + user + "(" + idUser + ") le " + date + ": (" + idMessage + ") " + message + "\n";
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static void addMessage(Message m) {
        messages.add(m);
    }

    public String printer() {
        String s = "";
        s += toString();
        for (int j = 0; j < Comment.getComments().size(); j++) {
            if (Comment.getComments().get(j).getPidMessage() == getIdMessage()) {
                s += Comment.getComments().get(j).printer(1);
            }
        }
        return s;
    }

    public void actualScore() {
        /*long diffInMillies = Math.abs(now - getDate().getTime());
        int diff = (int) TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        int sum = 20 - (diff / 30);
        setPersonalScore(now);*/
        setScore(20);
        setScoreByDate();
        int f = 0;
        for (int j = 0; j < Comment.getComments().size(); j++) {
            if (Comment.getComments().get(j).getPidMessage() == getIdMessage()) {
                f++;
                Comment.getComments().get(j).actualScore();
                addScore(Comment.getComments().get(j).getScore());
            }
        }
        if (score < 0) {
            setScore(0);
        }
        setFils(f);

    }

    public void addScore(int i) {
        score += i;
    }

    public void removeScore(int i) {
        score -= i;
    }

    public void setScoreByDate() {
        long diffInMillies = Math.abs(new Date().getTime() - getDate().getTime());
        int diff = (int) TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        setPersonalScore(diff);
        removeScore(diff/30);
    }
}
