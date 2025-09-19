package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
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
    player getDefaultFlorian() {
        return new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    }

    @Test
    @DisplayName("Unknown class test")
    void testUnknownClass() {
        player player = new player("Florian", "Grognak le barbare", "MAGE", 100, new ArrayList<>());
        assertNull(player.getAvatarClass());
    }

    @Test
    @DisplayName("Player name test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Avatar name test")
    void testAvatarName() {
        player player = getDefaultFlorian();
        assertThat(player.Avatar_name, is("Grognak le barbare"));
    }

    @Test
    @DisplayName("Get avatar class test")
    void testGetAvatarClass() {
        player p = getDefaultFlorian();

        assertThat(p.getAvatarClass(), is("ADVENTURER"));
    }

    @Test
    @DisplayName("Get money test")
    void testGetMoney() {
        player p = getDefaultFlorian();

        assertThat(p.money, is(100));
    }

    @Test
    @DisplayName("Get inventory test")
    void testGetInventory() {
        player p = getDefaultFlorian();

        assertThat(p.inventory.size(), is(0));
    }



    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = getDefaultFlorian();

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
        player p = getDefaultFlorian();

        p.removeMoney(100);
        assertThat(p.money, is(0));
    }


    @Test
    @DisplayName("Money addition test")
    void testAddMoney() {
        player p = getDefaultFlorian();

        p.addMoney(200);
        assertThat(p.money, is(300));
    }

    @Test
    @DisplayName("Level upgrade test")
    void testLevelUpgrade() {
        player p = getDefaultFlorian();

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
        player p = getDefaultFlorian();

        boolean newLevel = UpdatePlayer.addXp(p, 5);
        assertThat(p.retrieveLevel(), is(1));
        assertThat(p.getXp(), is(5));
        assertThat(newLevel, is(false));
    }

    @Test
    @DisplayName("Test KO")
    void testKO() {
        player p = getDefaultFlorian();

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

        player p = new player("Florian", "Grognak le nain", "DWARF", 100, i);
        p.currenthealthpoints = 1;
        p.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(3));
    }

    @Test
    @DisplayName("Dwarf No Potion fin de tour test")
    void testNoPotionInventaire() {
        player p = new player("Florian", "Grognak le nain", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 1;
        p.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(2));
    }

    @Test
    @DisplayName("Archer Magic Bow fin de tour test")
    void testMagicBowInventaire() {
        ArrayList<String> i = new ArrayList<>();
        i.add("Magic Bow");

        player p = new player("Florian", "Grognak l'archer", "ARCHER", 100, i);
        p.currenthealthpoints = 1;
        p.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(1));
    }

    @Test
    @DisplayName("Archer No Magic Bow fin de tour test")
    void testNoMagicBowInventaire() {
        player p = new player("Florian", "Grognak l'archer", "ARCHER", 100, new ArrayList<>());
        p.currenthealthpoints = 1;
        p.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(2));
    }

    @Test
    @DisplayName("Low level aventurer fin de tour test")
    void testLowLevelAv() {
        player p = getDefaultFlorian();
        p.currenthealthpoints = 1;
        p.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(2));
    }

    @Test
    @DisplayName("High level aventurer fin de tour test")
    void testHighLevelAv() {
        player p = getDefaultFlorian();
        p.currenthealthpoints = 1;
        p.healthpoints = 10;
        UpdatePlayer.addXp(p, 27);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(3));
    }

    @Test
    @DisplayName("Mid HP aventurer fin de tour test")
    void testMidHpAv() {
        player p = getDefaultFlorian();
        p.currenthealthpoints = 2;
        p.healthpoints = 4;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(2));
    }

    @Test
    @DisplayName("Above Mid HP aventurer fin de tour test")
    void testAboveMidHpAv() {
        player p = getDefaultFlorian();
        p.currenthealthpoints = 3;
        p.healthpoints = 4;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(3));
    }

    @Test
    @DisplayName("Max HP aventurer fin de tour test")
    void testMaxHpAv() {
        player p = getDefaultFlorian();
        p.currenthealthpoints = 4;
        p.healthpoints = 4;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(4));
    }

    @Test
    @DisplayName("Cheater HP aventurer fin de tour test")
    void testCheaterHpAv() {
        player p = getDefaultFlorian();
        p.currenthealthpoints = 5;
        p.healthpoints = 4;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(4));
    }
}
