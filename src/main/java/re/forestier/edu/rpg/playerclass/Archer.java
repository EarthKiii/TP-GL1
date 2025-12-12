package re.forestier.edu.rpg.playerclass;

import re.forestier.edu.rpg.Ability;
import re.forestier.edu.rpg.ItemArray;
import re.forestier.edu.rpg.Items;

import java.util.Map;

import static java.util.Map.entry;

public class Archer extends Player {
    private Archer(String playerName, String avatarName, int money, ItemArray inventory, Map<Integer, Map<Ability, Integer>> abilitiesMap) {
        super(playerName, avatarName, money, inventory, abilitiesMap);
    }

    public Archer(String playerName, String avatarName, int money, ItemArray inventory) {
        this(playerName, avatarName, money, inventory, Map.ofEntries(
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
        ));
    }

    @Override
    protected void endOfTurnUpdate() {
        addHP(1);
        if (inventory.contains(Items.MAGIC_BOW)) {
            setCurrentHP(getCurrentHP() + getCurrentHP() / 8 - 1);
        }
    }
}