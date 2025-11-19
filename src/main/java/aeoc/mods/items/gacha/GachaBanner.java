package aeoc.mods.items.gacha;

import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;


import java.util.ArrayList;
import java.util.List;


public class GachaBanner {
    public List<GachaDrop> drops = new ArrayList<>();
    public int pityMax = 50;
    public static final GachaBanner DEFAULT_BANNER = defaultBanner();


    public static GachaBanner defaultBanner() {
        GachaBanner b = new GachaBanner();
        b.drops.add(new GachaDrop(Items.IRON_INGOT, 50, "common"));
        b.drops.add(new GachaDrop(Items.GOLD_INGOT, 25, "uncommon"));
        b.drops.add(new GachaDrop(Items.DIAMOND, 10, "rare"));
        b.drops.add(new GachaDrop(Items.NETHERITE_SCRAP, 3, "epic"));
        b.drops.add(new GachaDrop(Items.NETHERITE_INGOT, 1, "legendary"));
        return b;
    }


    public GachaDrop roll(Random random) {
        int total = drops.stream().mapToInt(d -> d.weight).sum();
        int r = random.nextInt(total) + 1;
        int acc = 0;
        for (GachaDrop d : drops) {
            acc += d.weight;
            if (r <= acc) return d;
        }
        return drops.getFirst();
    }


    public GachaDrop rollWithPity(int pity, Random random) {
        if (pity >= pityMax) {
            for (GachaDrop d : drops) if ("legendary".equals(d.rarity)) return d;
        }
        return roll(random);
    }
}
