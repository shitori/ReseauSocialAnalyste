import java.rmi.Naming;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws Exception {
        Data d = new Data();
        Naming.rebind("DataRMI", d); // Rebind le nom à l'objet Data.

        d.init(); // Initialisation.
        while (true) {
            d.actualScore(); // Réactualisation des scores en fonction du temps.
            d.searchBest3(); // Cherche les 3 meilleurs commentaires en terme de score.
            try {
                Thread.sleep(5000); // Pause de 5 secondes.
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
