import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;



public class Comment {
    private Date date;
    private int idCommentaire;
    private int idUser;
    private String comment;
    private String user;
    private int pidCommentaire;
    private int pidMessage;
    private int score;
    private int fils;
    private long personalScore; // Variable permettant de vérifier la bonne actualisation.

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

    public Comment(Date d, int ic, int iu, String c, String u, int pidc, int pidm) {
        date = d;
        idCommentaire = ic;
        idUser = iu;
        comment = c;
        user = u;
        pidCommentaire = pidc;
        pidMessage = pidm;
        score = 20;
        fils = 0;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPidMessage() {
        return pidMessage;
    }

    public void setPidMessage(int pidMessage) {
        this.pidMessage = pidMessage;
    }

    public int getPidCommentaire() {
        return pidCommentaire;
    }

    public void setPidCommentaire(int pidCommentaire) {
        this.pidCommentaire = pidCommentaire;
    }

    @Override
    public String toString() {
        if (pidCommentaire == -1) {
            return "[" + fils + " fils/" + score + " score/" + personalScore + " score perso] Reponde de " + user + "(" + idUser + ") le " + date + " au message n°" + pidMessage + " : (" + idCommentaire + ") " + comment + "\n";

        }
        return "[" + fils + " fils/" + score + " score/" + personalScore + " score perso] Reponde de " + user + "(" + idUser + ") le " + date + " au commentaire n°" + pidCommentaire + " : (" + idCommentaire + ") " + comment + "\n";
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
     * Soustrait par rapport à la date
     * @param now
     */
    public void setScoreByDate(Date now) {

        long diffInMillies = Math.abs(now.getTime() - getDate().getTime());
        int diff = (int) TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        setPersonalScore(diff); // Permet de garder une trace de la différence entre le moment où le commentaire a été envoyé et maintenant.
        removeScore(diff/30);
    }
}
