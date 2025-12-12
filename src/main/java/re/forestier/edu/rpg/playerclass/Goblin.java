package re.forestier.edu.rpg.playerclass;

import re.forestier.edu.rpg.Ability;
import re.forestier.edu.rpg.ItemArray;

import java.util.Map;

import static java.util.Map.entry;

public class Goblin extends Player {
    private Goblin(String playerName, String avatarName, int money, ItemArray inventory, Map<Integer, Map<Ability, Integer>> abilitiesMap) {
        super(playerName, avatarName, money, inventory, abilitiesMap);
    }

    public Goblin(String playerName, String avatarName, int money, ItemArray inventory) {
        this(playerName, avatarName, money, inventory, Map.ofEntries(
                entry(1, Map.ofEntries(
                        entry(Ability.INT, 2),
                        entry(Ability.ATK, 2),
                        entry(Ability.ALC, 1)
                )),
                entry(2, Map.ofEntries(
                        entry(Ability.ATK, 3),
                        entry(Ability.ALC, 4)
                )),
                entry(3, Map.ofEntries(
                        entry(Ability.VIS, 1)
                )),
                entry(4, Map.ofEntries(
                        entry(Ability.DEF, 1)
                )),
                entry(5, Map.ofEntries(
                        entry(Ability.DEF, 2),
                        entry(Ability.ATK, 4)
                ))
        ));
    }
}