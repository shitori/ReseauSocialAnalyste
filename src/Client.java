import java.rmi.Naming;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost/DataRMI";
        DataRMI d = (DataRMI) Naming.lookup(url); // Retourne la référence de l'objet Data distant.
        System.out.println("Connected...");

        d.init(); // Initialisation.
        while (true) {
            d.actualScore(); // Réactualisation des scores en fonction du temps.
            d.searchBest3(); // Cherche les 3 meilleurs commentaires en terme de score.
            System.out.println(d.getBest3()); // Affiche les 3 meilleurs commentaires sous forme de chaîne de caractères.
            try {
                Thread.sleep(5000); // Pause de 5 secondes.
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
