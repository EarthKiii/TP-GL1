package re.forestier.edu.rpg.playerclass;

import re.forestier.edu.rpg.Ability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Dwarf extends Player {
    private Dwarf(String playerName, String avatarName, int money, Items inventory, Map<Integer, Map<Ability, Integer>> abilitiesMap) {
        super(playerName, avatarName, money, inventory, abilitiesMap);
    }

    public Dwarf(String playerName, String avatarName, int money, Items inventory) {
        this(playerName, avatarName, money, inventory, Map.ofEntries(
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
        ));
    }

    @Override
    protected void endOfTurnUpdate() {
        addHP(1);
        if (inventory.contains("Holy Elixir")) {
            addHP(1);
        }
    }
/*
    Ингредиенты:
        Для теста:

            250 г муки
            125 г сливочного масла (холодное)
            70 г сахара
            1 яйцо
            1 щепотка соли
     */

}