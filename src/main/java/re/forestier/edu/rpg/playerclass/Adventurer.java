package re.forestier.edu.rpg.playerclass;

import re.forestier.edu.rpg.Ability;
import re.forestier.edu.rpg.ItemArray;

import java.util.Map;

import static java.util.Map.entry;

public class Adventurer extends Player {
    private Adventurer(String playerName, String avatarName, int money, ItemArray inventory, Map<Integer, Map<Ability, Integer>> abilitiesMap) {
        super(playerName, avatarName, money, inventory, abilitiesMap);
    }

    public Adventurer(String playerName, String avatarName, int money, ItemArray inventory) {
        this(playerName, avatarName, money, inventory, Map.ofEntries(
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
        ));
    }

    @Override
    protected void endOfTurnUpdate() {
        addHP(level < 3 ? 1 : 2);
    }
}