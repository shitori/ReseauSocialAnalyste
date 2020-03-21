import java.util.Date;

public class Best {
    private int score;
    private String content;
    private int id;

    private static Best best3[] = {new Best(), new Best(), new Best()};

    public Best() {
        score = 0;
        content = "";
        id = -1;
    }

    public Best(int id, int s, String c) {
        this.id = id;
        this.score = s;
        this.content = c;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Best[] getBest3() {
        return best3;
    }

    public static void setBest3(Best[] best3) {
        Best.best3 = best3;
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

    public static void test() {

        for (int k = 0; k < Message.getMessages().size(); k++) {
            Message m = Message.getMessages().get(k);
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
        for (int k = 0; k < Comment.getComments().size(); k++) {
            Comment c = Comment.getComments().get(k);
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

    @Override
    public String toString() {
        return "score=" + score + ", content='" + content + '\n';
    }

    public static void actualScore() {
        Date now = new Date();
        System.out.println("Test à :" + now + "/" + now.getTime());
        for (int i = 0; i < Message.getMessages().size(); i++) {
            //Message.getMessages().get(i).actualScore(now.getTime());
            Message.getMessages().get(i).actualScore();
        }
    }

    public static String printer() {
        String s = "";
        for (int i = 0; i < Message.getMessages().size(); i++) {
            s += Message.getMessages().get(i).printer();
        }
        return s;
    }
}
