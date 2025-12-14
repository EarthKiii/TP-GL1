package re.forestier.edu.rpg;

public class Item implements Comparable<Item> {
    private final String name;
    private final String description;
    private final int weight;
    private final int value;

    public Item(String name, String description, int weight, int value) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return name.equals(item.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Item item) {
        if (this.value != item.value) {
            return CharSequence.compare(this.name, item.name);
        }
        return 0;
    }
}
