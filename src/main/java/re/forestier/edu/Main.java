package re.forestier.edu;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.playerclass.Dwarf;
import re.forestier.edu.rpg.ItemArray;
import re.forestier.edu.rpg.playerclass.Player;

public class Main {
    public static void main(String[] args) {
        Player firstPlayer = new Dwarf("Florian", "Ruzberg de Rivehaute",200, new ItemArray());
        firstPlayer.addMoney(400);

        firstPlayer.addXp(15);
        System.out.println(Affichage.afficherJoueur(firstPlayer));
        System.out.println("------------------");
        firstPlayer.addXp(20);
        System.out.println(Affichage.afficherJoueur(firstPlayer));
    }
}