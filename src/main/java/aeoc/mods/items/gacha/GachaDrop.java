package aeoc.mods.items.gacha;

import net.minecraft.item.Item;


public class GachaDrop {
    public Item item;
    public int weight;
    public String rarity;


    public GachaDrop(Item item, int weight, String rarity) {
        this.item = item;
        this.weight = weight;
        this.rarity = rarity;
    }
}