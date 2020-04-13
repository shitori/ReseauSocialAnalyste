import java.rmi.Naming;

public class Client {
    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost/DataRMI";
        DataRMI d = (DataRMI) Naming.lookup(url); // Retourne la référence de l'objet Data distant.

        System.out.println(d.getBest3()); // Affiche les 3 meilleurs commentaires au format XML.
    }
}
