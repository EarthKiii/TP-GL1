package re.forestier.edu.rpg.playerclass;

import re.forestier.edu.rpg.Ability;
import re.forestier.edu.rpg.Items;
import re.forestier.edu.rpg.ItemArray;

import java.util.*;

public abstract class Player {
    private final String playerName;
    private final String avatarName;

    protected int money;

    protected int level;
    protected int maxHP;
    protected int currentHP;
    protected int xp;

    protected int nextLevelMinXp;

    protected Map<Integer, Map<Ability, Integer>> abilitiesMap;
    protected Map<Ability, Integer> currentAbilities;
    protected ItemArray inventory;

    public int getLevel() {
        return level;
    }

    public Player(String playerName, String avatarName, int money, ItemArray inventory, Map<Integer, Map<Ability, Integer>> abilitiesMap) {
        this.abilitiesMap = abilitiesMap;
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.money = money;
        this.inventory = inventory;
        this.level = 1;
        updateNextLevelMinXp();
        currentAbilities = new HashMap<>(this.abilitiesMap.get(1));
    }

    private void updateNextLevelMinXp() {
        nextLevelMinXp = level * 10 + Math.round((float) ((level + 1) * nextLevelMinXp) / 4);
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

    public boolean addXp(int newXp) {
        xp += newXp;
        boolean newLevel = false;
        while (xp >= nextLevelMinXp) {
            updateNextLevelMinXp();
            level += 1;
            // Player leveled-up!
            // Give a random object
            ;
            Random random = new Random();
            inventory.add(Items.ALL_ITEMS.get(random.nextInt(Items.ALL_ITEMS.size())));

            // Add/upgrade abilities to player
            Map<Ability, Integer> newAbilities = abilitiesMap.get(level);
            newAbilities.forEach((ability, level) -> {
                currentAbilities.put(ability, newAbilities.get(ability));
            });
            if (!newLevel) newLevel = true;
        }
        return newLevel;
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

    public Map<Ability, Integer> getCurrentAbilities() {
        return currentAbilities;
    }

    public ItemArray getInventory() {
        return inventory;
    }

    public void sellItem(String itemName, int price) {
        if (inventory.remove(itemName)) {
            addMoney(price);
        } else {
            System.out.println("L'objet n'est pas dans l'inventaire.");
        }
    }

    public void endOfTurn() {
        if (currentHP == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        if (currentHP < maxHP / 2) {
            endOfTurnUpdate();
        }

        if (currentHP >= maxHP) {
            currentHP = maxHP;
        }

    }

    protected void endOfTurnUpdate() {};
}