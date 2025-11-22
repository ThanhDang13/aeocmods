package aeoc.mods;

import aeoc.mods.items.gacha.GachaGroup;
import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AEOCMod implements ModInitializer {
	public static final String MOD_ID = "aeocmod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");

        Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, "gacha_group"), GachaGroup.GACHA_GROUP);

	}
}