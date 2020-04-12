public class Best {
    private int score;
    private String content;
    private int id;
    private Comment comment;
    private Message message;

    public Best() {
        score = 0;
        content = "";
        id = -1;
        comment = null;
        message = null;
    }

    public Best(int id, int score, String content, Comment comment) {
        this.id = id;
        this.score = score;
        this.content = content; // Cette variable contient le toString() d'un message ou d'un commentaire.
        this.comment = comment;
        this.message = null;
    }

    public Best(int id, int score, String content, Message message) {
        this.id = id;
        this.score = score;
        this.content = content; // Cette variable contient le toString() d'un message ou d'un commentaire.
        this.comment = null;
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "score=" + score + ", content='" + content + '\n';
    }



}
