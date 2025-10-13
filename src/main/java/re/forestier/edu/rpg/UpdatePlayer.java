package re.forestier.edu.rpg;

import re.forestier.edu.rpg.playerclass.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;

public class UpdatePlayer {

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };

    public static Map<AvatarClass, Map<Integer, Map<Ability, Integer>>> abilitiesPerTypeAndLevel() {
        Map<AvatarClass, Map<Integer, Map<Ability, Integer>>> abilitiesPerTypeAndLevel = new HashMap<>();

        Map<Integer, Map<Ability, Integer>> adventurerMap = Map.ofEntries(
                entry(1, Map.ofEntries(
                        entry(Ability.INT, 1),
                        entry(Ability.DEF, 1),
                        entry(Ability.ATK, 3),
                        entry(Ability.CHA, 2)
                )),
                entry(2, Map.ofEntries(
                        entry(Ability.INT, 2),
                        entry(Ability.CHA, 3)
                )),
                entry(3, Map.ofEntries(
                        entry(Ability.ATK, 5),
                        entry(Ability.ALC, 1)
                )),
                entry(4, Map.ofEntries(
                        entry(Ability.DEF, 3)
                )),
                entry(5, Map.ofEntries(
                        entry(Ability.VIS, 1),
                        entry(Ability.DEF, 4)
                ))
        );
        abilitiesPerTypeAndLevel.put(AvatarClass.ADVENTURER, adventurerMap);

        Map<Integer, Map<Ability, Integer>> archerMap = Map.ofEntries(
                entry(1, Map.ofEntries(
                        entry(Ability.INT, 1),
                        entry(Ability.ATK, 3),
                        entry(Ability.CHA, 1),
                        entry(Ability.VIS, 3)
                )),
                entry(2, Map.ofEntries(
                        entry(Ability.DEF, 1),
                        entry(Ability.CHA, 2)
                )),
                entry(3, Map.ofEntries(
                        entry(Ability.ATK, 3)
                )),
                entry(4, Map.ofEntries(
                        entry(Ability.DEF, 2)
                )),
                entry(5, Map.ofEntries(
                        entry(Ability.ATK, 4)
                ))
        );
        abilitiesPerTypeAndLevel.put(AvatarClass.ARCHER, archerMap);

        Map<Integer, Map<Ability, Integer>> dwarfMap = Map.ofEntries(
                entry(1, Map.ofEntries(
                        entry(Ability.ALC, 4),
                        entry(Ability.INT, 1),
                        entry(Ability.ATK, 3)
                )),
                entry(2, Map.ofEntries(
                        entry(Ability.DEF, 1),
                        entry(Ability.ALC, 5)
                )),
                entry(3, Map.ofEntries(
                        entry(Ability.ATK, 4)
                )),
                entry(4, Map.ofEntries(
                        entry(Ability.DEF, 2)
                )),
                entry(5, Map.ofEntries(
                        entry(Ability.CHA, 1)
                ))
        );
        abilitiesPerTypeAndLevel.put(AvatarClass.DWARF, dwarfMap);

        return abilitiesPerTypeAndLevel;
    }

    public static boolean addXp(Player player, int xp) {
        int currentLevel = player.retrieveLevel();
        player.setXp(player.getXp() + xp);
        int newLevel = player.retrieveLevel();

        if (newLevel != currentLevel) {
            // Player leveled-up!
            // Give a random object
            ;
            Random random = new Random();
            player.inventory.add(objectList[random.nextInt(objectList.length)]);

            // Add/upgrade abilities to player
            Map<Ability, Integer> abilities = abilitiesPerTypeAndLevel().get(player.getAvatarClass()).get(newLevel);
            abilities.forEach((ability, level) -> {
                player.abilities.put(ability, abilities.get(ability));
            });
            return true;
        }
        return false;
    }

    // majFinDeTour met à jour les points de vie
    public static void majFinDeTour(Player player) {
        if (player.getCurrentHP() == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        if (player.getCurrentHP() < player.getMaxHP() /2) {
            switch (player.getAvatarClass()) {
                case ADVENTURER:
                    player.addHP(player.retrieveLevel() < 3 ? 1 : 2);
                    break;
                case DWARF:
                    player.addHP(1);
                    if (player.inventory.contains("Holy Elixir")) {
                        player.addHP(1);
                    }
                    break;
                case ARCHER:
                    player.addHP(1);
                    if (player.inventory.contains("Magic Bow")) {
                        player.setCurrentHP(player.getCurrentHP() + player.getCurrentHP() / 8 - 1);
                    }
            }
        }

        if (player.getCurrentHP() >= player.getMaxHP()) {
            player.setCurrentHP(player.getMaxHP());
        }
    }
}