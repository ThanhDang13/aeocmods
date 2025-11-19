package aeoc.mods.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static aeoc.mods.AEOCMod.MOD_ID;

public class ModItems {


    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
}
