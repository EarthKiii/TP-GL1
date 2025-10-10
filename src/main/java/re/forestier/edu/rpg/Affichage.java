package re.forestier.edu.rpg;

import java.util.Map.Entry;

public class Affichage {

    public static String afficherJoueur(Player player) {
        return String.format("""
Joueur %s joué par %s
Niveau : %d (XP totale : %d)

Capacités :%s

Inventaire :%s""",
                player.getAvatarName(),
                player.getPlayerName(),
                player.retrieveLevel(),
                player.getXp(),
                String.join("",player.abilities.entrySet().stream().sorted(Entry.comparingByKey()).map(capacity -> "\n   " + capacity.getKey() + " : " + capacity.getValue()).toList()),
                String.join("",player.inventory.stream().sorted().map(item -> "\n   " + item).toList())
        );
    }
}
