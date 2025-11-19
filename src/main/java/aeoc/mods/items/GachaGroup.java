package aeoc.mods.items;

import aeoc.mods.items.gacha.GachaCapsuleItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static aeoc.mods.AEOCMod.MOD_ID;
import static aeoc.mods.items.ModItems.registerItem;

public final class GachaGroup {
    public static Item GACHA_CAPSULE = registerItem("gacha_capsule", new GachaCapsuleItem(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "gacha_capsule"))).maxCount(16)));
    public static final ItemGroup GACHA_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(GACHA_CAPSULE))
            .displayName(Text.translatable("Gacha"))
            .entries((context, entries) -> {
                entries.add(GACHA_CAPSULE);
            })
            .build();
}