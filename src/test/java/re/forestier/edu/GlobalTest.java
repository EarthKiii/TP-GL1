package re.forestier.edu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        player player = new player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
        UpdatePlayer.addXp(player, 1);

        verify(Affichage.afficherJoueur(player));
    }

    @Test
    void testAffichageInv() {
        ArrayList<String> i = new ArrayList<>();
        i.add("Holy Elixir");
        player player = new player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, i);
        UpdatePlayer.addXp(player, 1);

        verify(Affichage.afficherJoueur(player));
    }
}
