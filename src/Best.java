public class Best {
    private int score;
    private String content;
    private int id;

    public Best() {
        score = 0;
        content = "";
        id = -1;
    }

    public Best(int id, int s, String c) {
        this.id = id;
        this.score = s;
        this.content = c; // Cette variable contient le toString() d'un message ou d'un commentaire.

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


    @Override
    public String toString() {
        return "score=" + score + ", content='" + content + '\n';
    }



}
