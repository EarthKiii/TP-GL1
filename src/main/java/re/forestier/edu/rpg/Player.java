package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String playerName;
    private String avatarName;
    private AvatarClass avatarClass;

    private int money;

    private int level;
    private int maxHP;
    private int currentHP;
    private int xp;


    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    public Player(String playerName, String avatarName, AvatarClass avatarClass, int money, ArrayList<String> inventory) {

        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(this.avatarClass).get(1);
    }

    public AvatarClass getAvatarClass () {
        return avatarClass;
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
        HashMap<Integer, Integer> levels = new HashMap<>();
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

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public void setAvatarClass(AvatarClass avatarClass) {
        this.avatarClass = avatarClass;
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
        if (hp > 0 && currentHP <= maxHP) this.currentHP += hp;
    }

    public void removeHP(int hp) {
        if (hp > 0 && currentHP - hp >= 0) this.currentHP -= hp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getMoney() {
        return money;
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