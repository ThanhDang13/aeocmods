package aeoc.mods.items.gacha;


import aeoc.mods.utils.IEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static aeoc.mods.items.gacha.GachaBanner.DEFAULT_BANNER;

public class GachaCapsuleItem extends Item {
    public static final Logger LOGGER = LoggerFactory.getLogger("GachaCapsuleItem");

    public GachaCapsuleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            performRoll((IEntityDataSaver) player, player, stack, user.getActiveHand(), player.getWorld());
        }

        stack.decrement(1);

        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public void performRoll(@NotNull IEntityDataSaver dataSaver, ServerPlayerEntity player, ItemStack stack, Hand hand, World world) {
        NbtCompound persistent = dataSaver.getPersistentData();
        int pity = persistent.getInt("pity").orElse(0);
        int rolls = persistent.getInt("rolls").orElse(0);


        GachaDrop result = DEFAULT_BANNER.rollWithPity(pity, world.random);

        if (pity >= DEFAULT_BANNER.pityMax) {
            pity = 0;
        } else {
            pity++;
        }
        rolls++;

        persistent.putInt("pity", pity);
        persistent.putInt("rolls", rolls);

        ItemStack reward = new ItemStack(result.item);
        boolean added = player.getInventory().insertStack(reward);
        if (!added) {
            player.dropItem(reward, false);
        }
    }
}
