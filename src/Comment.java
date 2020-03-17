import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

    public String toString() {
        if (pidCommentaire == -1) {
            return "Reponde de " + user + "(" + idUser + ") le " + date + " au message n°" + pidMessage + " : (" + idCommentaire + ") " + comment + "\n";

        }
        return "Reponde de " + user + "(" + idUser + ") le " + date + " au commentaire n°" + pidCommentaire + " : (" + idCommentaire + ") " + comment + "\n";

        //return idCommentaire+"->"+score;

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static void addComment(Comment c){
        comments.add(c);
    }

    public static String toStringA(){
        String s;
        s = Arrays.toString(new ArrayList[]{comments});
        return s;
    }
	
	public void realScore() {
		/*Predicate<Comment> bypidC = comments -> comments.pidCommentaire==idCommentaire ;
        double ns = comments
                .stream()
                .filter(bypidC)
                //.peek(Comment::realScore)
                .map(Comment::getScore)
                .reduce(0, Integer::sum);
		setScore(20+(int) ns);*/
        int sum = (int) (20-(((new Date().getTime()-getDate().getTime())/1000)%30));
		for (int i=0;i<comments.size();i++){
		    if (comments.get(i).getPidCommentaire()==getIdCommentaire()){
                comments.get(i).realScore();
		        sum+=comments.get(i).getScore();
            }
        }
		setScore(sum);
	}


	public static void applyRealScore(){
        for (int i=0;i<comments.size();i++){
            comments.get(i).realScore();
        }
    }

}
