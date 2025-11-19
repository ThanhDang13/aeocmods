package aeoc.mods.items.gacha;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import static aeoc.mods.items.gacha.GachaBanner.DEFAULT_BANNER;

public class GachaCapsuleItem extends Item {


    public GachaCapsuleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            performRoll(player, stack, user.getActiveHand());
        }

        stack.decrement(1);

        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public void performRoll(@NotNull ServerPlayerEntity player, ItemStack stack, Hand hand) {
//        NbtCompound persistent = player.getPersistentData();
//        NbtCompound gachaTag = persistent.getCompound("gacha_data")
//                .orElseGet(() -> {
//                    NbtCompound newTag = new NbtCompound();
//                    persistent.put("gacha_data", newTag);
//                    return newTag;
//                });
//
//        int pity = gachaTag.getInt("pity").orElse(0);
//        int rolls = gachaTag.getInt("rolls").orElse(0);

        // Example: roll with pity
        GachaDrop result = DEFAULT_BANNER.rollWithPity(0, player.getWorld().random);

        // Give item to player
        ItemStack reward = new ItemStack(result.item);
        boolean added = player.getInventory().insertStack(reward);
        if (!added) {
            player.dropItem(reward, false);
        }

        // Update pity logic here (increment or reset)
    }
}
