package trnrr.toggleablelamps;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlockLamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.CreativeEntry;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class ToggleableLamps implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "toggleablelamps";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static final Block toggledLampIdle = new BlockBuilder(MOD_ID)
		.build(new ToggleLampBlock("toggledLampIdle",Config.cfg.getInt("Item IDs.StartingID"), false)
			.withHardness(0.5F)
			.withDisabledStats()
			.withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU));
	public static final Block toggledLampActive = new BlockBuilder(MOD_ID)
		.build(new ToggleLampBlock("toggledLampActive",Config.cfg.getInt("Item IDs.StartingID")+1, true)
			.withLightEmission(0.9375F)
			.withHardness(0.5F)
			.withDisabledStats()
			.withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU));

	static {
		Item.itemsList[toggledLampIdle.id] = new ItemBlockLamp((toggledLampIdle));
	}

    @Override
    public void onInitialize() {
        LOGGER.info("ToggleableLamps initialized.");

		for(int i = 0; i <= 15; i++)
		{
			CreativeEntry test = new CreativeEntry(new ItemStack(toggledLampIdle, 1, i));
			CreativeEntry.addEntry(test);
		}
    }

	@Override
	public void beforeGameStart() {
	}

	@Override
	public void afterGameStart() {
	}

	@Override
	public void onRecipesReady() {

		// New lamp recipes
		for(int i = 0; i <= 15; i++)
		{
			RecipeBuilder.Shapeless(MOD_ID)
				.addInput(Block.leverCobbleStone)
				.addInput(Block.lampIdle, i)
				.create("toggledLampIdle_"+i, new ItemStack(toggledLampIdle, 1, i));
		}
		// Craft back to original lamps
		for(int i = 0; i <= 15; i++)
		{
			RecipeBuilder.Shapeless(MOD_ID)
				.addInput(toggledLampIdle, i)
				.create("lampIdle_"+i, new ItemStack(Block.lampIdle, 1, i));
		}
	}
}
