import java.io.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Data extends UnicastRemoteObject implements DataRMI {
    private ArrayList<Message> messages; // Liste de tous les messages.
    private ArrayList<Comment> comments; // Liste de tous les commentaires.
    private Best best3[]; // Liste des 3 meilleurs messages et/ou commentaires

    public Data() throws RemoteException { // Initialise les 3 listes avec des éléments null ou vide.
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

    public void init() throws RemoteException { // Initialise les listes de commentaires et de messages par rapport au fichier.
        BufferedReader reader;
        try {
            Date d = new Date(); // date actuel
            URL url = new File("reseauSocial.txt").toURI().toURL(); // Url du fichier.
            reader = new BufferedReader(new InputStreamReader(url.openStream())); // Ouverture du fichier.
            String line = reader.readLine(); // Lecture ligne par ligne.
            while (line != null) { // Tant que l'on est pas à la fin du fichier on lit les lignes une par une.
                long time = ((d.getTime() - 500000)) + (int) (Math.random() * ((d.getTime()) - (d.getTime() - 500000))); // Génère une date au hasard entre maintenant et il y a 5 minutes.
                String[] arrOfStr = line.split("\\|"); // On sépare les éléments en selon les "|".
                if (arrOfStr.length == 4) { // S'il y a 4 éléments, c'est un message.
                    messages.add(new Message(new Date(time), Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]), arrOfStr[2], arrOfStr[3]));
                } else if (arrOfStr.length == 5) { // S'il y en a 5, c'est un commentaire de message.
                    comments.add(new Comment(new Date(time), Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]), arrOfStr[2], arrOfStr[3], Integer.parseInt(arrOfStr[4]), -1));
                } else if (arrOfStr.length == 6) { // S'il y en a 6, c'est un commentaire de commentaire.
                    comments.add(new Comment(new Date(time), Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]), arrOfStr[2], arrOfStr[3], -1, Integer.parseInt(arrOfStr[5])));
                } else { // Sinon, ce n'est pas reconnue
                    System.err.println("ligne non reconnue");
                }
                line = reader.readLine(); // Fermeture du fichier.
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchBest3() throws RemoteException {
        best3 = new Best[]{new Best(), new Best(), new Best()}; // On réinitialise la liste best3.
        for (int k = 0; k < getMessages().size(); k++) { // Pour chaque message de la listes de messages ...
            Message m = getMessages().get(k);
            for (int i = 0; i < best3.length; i++) { // Pour chaque message ou commentaire de la liste best3 ...
                if (m.getScore() > best3[i].getScore()) { // Si le message à un score plus élevé ...
                    boolean present = false;
                    for (int j = 0; j < best3.length; j++) { // On vérifie si le message n'est pas déjà dans le liste best3.
                        if (best3[j].getId() == m.getIdMessage()) {
                            present = true;
                        }
                    }
                    if (!present) { // S'il n'est pas présent alors on l'ajoute.
                        Best newb = new Best(m.getIdMessage(), m.getScore(), m.toString());
                        best3[i] = newb;
                    }
                }
            }
        }
        // Même procédé mais avec les commentaires.
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

    /**
     * Cette fonction réactualise les scores en fonction du temps.
     * @throws RemoteException
     */
    public void actualScore() throws RemoteException{
        Date now = new Date();
        System.out.println("Test à :" + now + "/" + now.getTime());
        for (int i = 0; i < getMessages().size(); i++) {
            Message m = actualScoreMessages(now, i);
            messages.set(i, m);
        }
    }

    /**
     * Cette fonction actualise le score d'un message de la liste des messages.
     * @param now
     * @param index
     * @return
     */
    public Message actualScoreMessages(Date now, int index) {
        Message m = getMessages().get(index);
        m.setScore(20);
        m.setScoreByDate(now);
        m.setFils(0);
        for (int i = 0; i < getComments().size(); i++) { // Avant d'actualiser le score d'un message, il faut actualiser celui de ses commentaires liés.
            if (getComments().get(i).getPidMessage() == m.getIdMessage()) {
                m.setFils(m.getFils() + 1);
                Comment c = actualScoreComments(now, i);
                comments.set(i, c);
                m.addScore(comments.get(i).getScore());
            }
        }
        if (m.getScore() < 0) { // Si le score est négatif, alors on le met à 0.
            m.setScore(0);
        }
        return m;
    }

    /**
     *
     * Même procedé qu'actualScoreMessage.
     * @param now
     * @param index
     * @return
     */
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
