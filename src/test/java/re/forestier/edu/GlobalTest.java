package re.forestier.edu;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Items;
import re.forestier.edu.rpg.playerclass.Adventurer;
import re.forestier.edu.rpg.ItemArray;
import re.forestier.edu.rpg.playerclass.Player;

import static org.approvaltests.Approvals.verify;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        Player player = new Adventurer("Florian", "Gnognak le Barbare", 200, new ItemArray());
        player.addXp(1);

        verify(Affichage.afficherJoueur(player));
    }

    @Test
    void testAffichageInv() {
        ItemArray i = new ItemArray();
        i.add(Items.HOLY_ELIXIR);
        Player player = new Adventurer("Florian", "Gnognak le Barbare", 200, i);
        player.addXp(1);

        verify(Affichage.afficherJoueur(player));
    }

    @Test
    void testAffichageBaseMd() {
        Player player = new Adventurer("Florian", "Gnognak le Barbare", 200, new ItemArray());
        player.addXp(1);

        verify(Affichage.afficherJoueurMd(player));
    }

    @Test
    void testAffichageInvMd() {
        ItemArray i = new ItemArray();
        i.add(Items.HOLY_ELIXIR);
        Player player = new Adventurer("Florian", "Gnognak le Barbare", 200, i);
        player.addXp(15);

        verify(Affichage.afficherJoueurMd(player));
    }
}
