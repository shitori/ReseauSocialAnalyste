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

    private long personalScore; // Variable de stockage.

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

    public void addScore(int i) {
        score += i;
    }

    public void removeScore(int i) {
        score -= i;
    }

    /**
     * Soustrait par rapport à la date.
     * @param now
     */
    public void setScoreByDate(Date now) {
        long diffInMillies = Math.abs(now.getTime() - getDate().getTime());
        int diff = (int) TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        setPersonalScore(diff); // Permet de garder une trace de la différence entre le moment où le commentaire a été envoyé et maintenant.
        removeScore(diff/30);
    }
}
