import java.rmi.Naming;

public class Client {
    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            String url = "rmi://" + args[0] + "/Data";
            DataRMI d = (DataRMI) Naming.lookup(url); // Retourne la référence de l'objet Data distant.

            System.out.println(d.getBest3()); // Affiche les 3 meilleurs commentaires au format XML.
        }
        else {
            System.err.println("Argument manquant !");
        }
    }
}
