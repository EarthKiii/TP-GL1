package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UnitTests {
    Player getDefaultFlorian() {
        return new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    }

    @Test
    @DisplayName("Unknown class test")
    void testUnknownClass() {
        Player player = new Player("Florian", "Grognak le barbare", "MAGE", 100, new ArrayList<>());
        assertNull(player.getAvatarClass());
    }

    @Test
    @DisplayName("Player name test")
    void testPlayerName() {
        Player player = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.getPlayerName(), is("Florian"));
    }

    @Test
    @DisplayName("Avatar name test")
    void testAvatarName() {
        Player player = getDefaultFlorian();
        assertThat(player.getAvatarName(), is("Grognak le barbare"));
    }

    @Test
    @DisplayName("Get avatar class test")
    void testGetAvatarClass() {
        Player p = getDefaultFlorian();

        assertThat(p.getAvatarClass(), is("ADVENTURER"));
    }

    @Test
    @DisplayName("Get money test")
    void testGetMoney() {
        Player p = getDefaultFlorian();

        assertThat(p.getMoney(), is(100));
    }

    @Test
    @DisplayName("Get inventory test")
    void testGetInventory() {
        Player p = getDefaultFlorian();

        assertThat(p.inventory.size(), is(0));
    }



    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        Player p = getDefaultFlorian();

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testRemoveMoney() {
        Player p = getDefaultFlorian();

        p.removeMoney(100);
        assertThat(p.getMoney(), is(0));
    }


    @Test
    @DisplayName("Money addition test")
    void testAddMoney() {
        Player p = getDefaultFlorian();

        p.addMoney(200);
        assertThat(p.getMoney(), is(300));
    }

    @Test
    @DisplayName("Level upgrade test")
    void testLevelUpgrade() {
        Player p = getDefaultFlorian();

        HashMap<Integer, Integer> levels = new HashMap<>();
        levels.put(2,10); // 1*10 + ((2*0)/4)
        levels.put(3,27); // 2*10 + ((3*10)/4)
        levels.put(4,57); // 3*10 + ((4*27)/4)
        levels.put(5,111); // 4*10 + ((5*57)/4)

        for (Map.Entry<Integer, Integer> level: levels.entrySet()) {
            boolean newLevel = UpdatePlayer.addXp(p, level.getValue() - p.getXp());
            assertThat(p.retrieveLevel(), is(level.getKey()));
            assertThat(newLevel, is(true));
        }
    }

    @Test
    @DisplayName("Low xp gain test")
    void testLowXpGain() {
        Player p = getDefaultFlorian();

        boolean newLevel = UpdatePlayer.addXp(p, 5);
        assertThat(p.retrieveLevel(), is(1));
        assertThat(p.getXp(), is(5));
        assertThat(newLevel, is(false));
    }

    @Test
    @DisplayName("Test KO")
    void testKO() {
        Player p = getDefaultFlorian();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        UpdatePlayer.majFinDeTour(p);
        assertThat(outContent.toString(), is("Le joueur est KO !\n"));
    }

    @Test
    @DisplayName("Dwarf potion fin de tour test")
    void testPotionInventaire() {
        ArrayList<String> i = new ArrayList<>();
        i.add("Holy Elixir");

        Player p = new Player("Florian", "Grognak le nain", "DWARF", 100, i);
        p.setCurrentHP(1);
        p.setMaxHP(10);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.getCurrentHP(), is(3));
    }

    @Test
    @DisplayName("Dwarf No Potion fin de tour test")
    void testNoPotionInventaire() {
        Player p = new Player("Florian", "Grognak le nain", "DWARF", 100, new ArrayList<>());
        p.setCurrentHP(1);
        p.setMaxHP(10);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.getCurrentHP(), is(2));
    }

    @Test
    @DisplayName("Archer Magic Bow fin de tour test")
    void testMagicBowInventaire() {
        ArrayList<String> i = new ArrayList<>();
        i.add("Magic Bow");

        Player p = new Player("Florian", "Grognak l'archer", "ARCHER", 100, i);
        p.setCurrentHP(1);
        p.setMaxHP(10);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.getCurrentHP(), is(1));
    }

    @Test
    @DisplayName("Archer No Magic Bow fin de tour test")
    void testNoMagicBowInventaire() {
        Player p = new Player("Florian", "Grognak l'archer", "ARCHER", 100, new ArrayList<>());
        p.setCurrentHP(1);
        p.setMaxHP(10);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.getCurrentHP(), is(2));
    }

    @Test
    @DisplayName("Low level aventurer fin de tour test")
    void testLowLevelAv() {
        Player p = getDefaultFlorian();
        p.setCurrentHP(1);
        p.setMaxHP(10);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.getCurrentHP(), is(2));
    }

    @Test
    @DisplayName("High level aventurer fin de tour test")
    void testHighLevelAv() {
        Player p = getDefaultFlorian();
        p.setCurrentHP(1);
        p.setMaxHP(10);
        UpdatePlayer.addXp(p, 27);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.getCurrentHP(), is(3));
    }

    @Test
    @DisplayName("Mid HP aventurer fin de tour test")
    void testMidHpAv() {
        Player p = getDefaultFlorian();
        p.setCurrentHP(2);
        p.setMaxHP(4);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.getCurrentHP(), is(2));
    }

    @Test
    @DisplayName("Above Mid HP aventurer fin de tour test")
    void testAboveMidHpAv() {
        Player p = getDefaultFlorian();
        p.setCurrentHP(3);
        p.setMaxHP(4);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.getCurrentHP(), is(3));
    }

    @Test
    @DisplayName("Max HP aventurer fin de tour test")
    void testMaxHpAv() {
        Player p = getDefaultFlorian();
        p.setCurrentHP(4);
        p.setMaxHP(4);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.getCurrentHP(), is(4));
    }

    @Test
    @DisplayName("Cheater HP aventurer fin de tour test")
    void testCheaterHpAv() {
        Player p = getDefaultFlorian();
        p.setCurrentHP(5);
        p.setMaxHP(4);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.getCurrentHP(), is(4));
    }
}
