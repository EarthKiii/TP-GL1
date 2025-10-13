package re.forestier.edu.rpg.playerclass;

import re.forestier.edu.rpg.Ability;

import java.util.ArrayList;
import java.util.Map;

public class Archer extends Player {
    public Map<Ability, Integer> abilities;
    public Archer(String playerName, String avatarName, int money, ArrayList<String> inventory) {
        super(playerName, avatarName, money, inventory);
        // abilities = new HashMap<>(UpdatePlayer.abilitiesPerTypeAndLevel().get(this.avatarClass).get(1));
    }

    @Override
    public void endOfTurn() {
        addHP(1);
        if (inventory.contains("Magic Bow")) {
            setCurrentHP(getCurrentHP() + getCurrentHP() / 8 - 1);
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