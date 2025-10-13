package re.forestier.edu.rpg.playerclass;

import re.forestier.edu.rpg.Ability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Player {
    private final String playerName;
    private final String avatarName;

    protected int money;

    protected int level;
    protected int maxHP;
    protected int currentHP;
    protected int xp;

    protected Map<Integer, Map<Ability, Integer>> abilitiesMap;
    protected Map<Ability, Integer> currentAbilities;

    public ArrayList<String> inventory;
    public Player(String playerName, String avatarName, int money, ArrayList<String> inventory) {
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.money = money;
        this.inventory = inventory;
        currentAbilities = new HashMap<>(getAbilitiesMap().get(1));
        // this.abilities = new HashMap<>(UpdatePlayer.abilitiesPerTypeAndLevel().get(this.avatarClass).get(1));
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }

        money -= amount;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public int retrieveLevel() {
        // (lvl-1) * 10 + round((lvl * xplvl-1)/4)
        Map<Integer, Integer> levels = new HashMap<>();
        levels.put(2,10); // 1*10 + ((2*0)/4)
        levels.put(3,27); // 2*10 + ((3*10)/4)
        levels.put(4,57); // 3*10 + ((4*27)/4)
        levels.put(5,111); // 4*10 + ((5*57)/4)
        //TODO : ajouter les prochains niveaux

        if (xp < levels.get(2)) {
            return 1;
        }
        else if (xp < levels.get(3)) {return 2;
        }
        if (xp < levels.get(4)) {
            return 3;
        }
        if (xp < levels.get(5)) return 4;
        return 5;
    }

    public int getXp() {
        return this.xp;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void addHP(int hp) {
        if (currentHP + hp > maxHP && hp < 0) {
            throw new IllegalArgumentException("Player can't have a negative HPs!");
        }
        currentHP += hp;
    }

    public void removeHP(int hp) {
        if (currentHP - hp < 0 && hp < 0) {
            throw new IllegalArgumentException("Player can't have a more than max HPs!");
        }
        currentHP -= hp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getMoney() {
        return money;
    }

    public abstract void endOfTurn();

    protected Map<Integer, Map<Ability, Integer>> getAbilitiesMap() {
        return abilitiesMap;
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