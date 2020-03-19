import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    public int getFils() {
        return fils;
    }

    public void setFils(int fils) {
        this.fils = fils;
    }

    private static ArrayList<Comment> comments = new ArrayList<>();

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

    public static ArrayList<Comment> getComments() {
        return comments;
    }

    public static void setComments(ArrayList<Comment> comments) {
        Comment.comments = comments;
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
            return fils+" fils: Reponde de " + user + "(" + idUser + ") le " + date + " au message n°" + pidMessage + " : (" + idCommentaire + ") " + comment + "\n";

        }
        return fils+" fils: Reponde de " + user + "(" + idUser + ") le " + date + " au commentaire n°" + pidCommentaire + " : (" + idCommentaire + ") " + comment + "\n";
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static void addComment(Comment c) {
        comments.add(c);
    }

    public void printer(int nbTab) {
        for (int i = 0; i < nbTab; i++) {
            System.out.print("\t");
        }
        System.out.println(this);
        for (int i = 0; i < comments.size(); i++) {
            if (comments.get(i).getPidCommentaire() == getIdCommentaire()) {
                comments.get(i).printer(nbTab + 1);
            }
        }
    }

    public void actualScore(Date now) {
        long diffInMillies = Math.abs(now.getTime() - getDate().getTime());
        int diff = (int) TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        int sum = 20 - (diff % 30);
        int f=0;
        //int sum = 20;
        for (int j = 0; j < comments.size(); j++) {
            if (comments.get(j).getPidCommentaire() == getIdCommentaire()) {
                f++;
                comments.get(j).actualScore(now);
                sum += comments.get(j).getScore();
            }
        }
        if (sum < 0) {
            sum = 0;
        }
        setFils(f);
        setScore(sum);
    }
}
