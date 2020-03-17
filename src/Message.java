import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Predicate;

public class Message {
    private Date date;
    private int idMessage;
    private int idUser;
    private String message;
    private String user;
    private int score;
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
        return "De " + user + "(" + idUser + ") le " + date + ": (" + idMessage + ") " + message + "\n";
        //return idMessage+"->"+score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static void addMessage(Message m){
    	messages.add(m);
	}

    public static String toStringA(){
        String s;
        s = Arrays.toString(new ArrayList[]{messages});
        return s;
    }

    public void realScore() {
        int sum = (int) (20-(((new Date().getTime()-getDate().getTime())/1000)%30));
        ArrayList<Comment> comments = Comment.getComments();
        for (int i=0;i<comments.size();i++){
            if (comments.get(i).getPidMessage()==getIdMessage()){
                sum+=comments.get(i).getScore();
            }
        }
        setScore(sum);
    }
    public static void applyRealScore(){
        for (int i=0;i<messages.size();i++){
            messages.get(i).realScore();
        }
    }
}
