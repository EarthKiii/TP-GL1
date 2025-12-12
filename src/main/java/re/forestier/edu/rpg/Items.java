package re.forestier.edu.rpg;

import java.util.Arrays;
import java.util.List;

public class Items {
    public final static Item LOOKOUT_RING = new Item("Lookout Ring", "Prevents surprise attacks", 1, 100);
    public final static Item SCROLL_OF_STUPIDITY = new Item("Scroll of Stupidity", "INT-2 when applied to an enemy", 1, 150);
    public final static Item DRAUPNIR = new Item("Draupnir", "Increases XP gained by 100%", 2, 300);
    public final static Item MAGIC_CHARM = new Item("Magic Charm", "Magic +10 for 5 rounds", 1, 200);
    public final static Item RUNE_STAFF_OF_CURSE = new Item("Rune Staff of Curse", "May burn your enemies... Or yourself. Who knows?", 3, 400);
    public final static Item COMBAT_EDGE = new Item("Combat Edge", "Well, that's an edge", 2, 250);
    public final static Item HOLY_ELIXIR = new Item("Holy Elixir", "Recover your HP", 1, 150);
    public final static Item MAGIC_BOW = new Item("Magic Bow", "Recover some HP at the end of your turn", 3, 350);
    public final static List<Item> ALL_ITEMS = Arrays.asList(LOOKOUT_RING, SCROLL_OF_STUPIDITY, DRAUPNIR, MAGIC_CHARM, RUNE_STAFF_OF_CURSE, COMBAT_EDGE, HOLY_ELIXIR, MAGIC_BOW);
}
