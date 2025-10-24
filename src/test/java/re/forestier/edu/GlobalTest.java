package re.forestier.edu;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.playerclass.Adventurer;
import re.forestier.edu.rpg.playerclass.Items;
import re.forestier.edu.rpg.playerclass.Player;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        Player player = new Adventurer("Florian", "Gnognak le Barbare", 200, new Items());
        player.addXp(1);

        verify(Affichage.afficherJoueur(player));
    }

    @Test
    void testAffichageInv() {
        Items i = new Items();
        i.add("Holy Elixir");
        Player player = new Adventurer("Florian", "Gnognak le Barbare", 200, i);
        player.addXp(1);

        verify(Affichage.afficherJoueur(player));
    }
}
