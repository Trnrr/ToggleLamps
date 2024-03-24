package turniplabs.examplemod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.block.color.BlockColor;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLayerBase;
import net.minecraft.core.block.BlockLeavesBase;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;
import net.minecraft.core.item.block.ItemBlockLamp;
import net.minecraft.core.item.block.ItemBlockLayer;
import net.minecraft.core.item.block.ItemBlockLeaves;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import net.minecraft.core.item.Item;


public class ExampleMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "examplemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Item[] itemsList = new Item['耀'];
	public static final Block[] blocksList = new Block[16384];


	public static final Block toggledLampIdle = new BlockBuilder(MOD_ID)
		//.addTags(new Tag[2])
		.build(new LampBlock("toggledLampIdle",8888, false)
			.withHardness(0.5F)
			.withDisabledStats()
			//.withDisabledNeighborNotifyOnMetadataChange()
			.withTags(BlockTags.MINEABLE_BY_PICKAXE));
	public static final Block toggledLampActive = new BlockBuilder(MOD_ID)
		//.addTags(new Tag[2])
		.build(new LampBlock("toggledLampActive",8889, true)
			.withLightEmission(0.9375F)
			.withHardness(0.5F)
			.withDisabledStats()
			//.withDisabledNeighborNotifyOnMetadataChange()
			.withTags(BlockTags.MINEABLE_BY_PICKAXE));


/*	static
	{
		Item.itemsList[toggleLampIdle.id] = new ItemBlockLamp(toggleLampIdle);
		int i;
		for(i = 0; i < blocksList.length; ++i) {

		}

	}*/

	//lampActive = (new BlockLamp("lamp.active", 851, true)).withLightEmission(0.9375F).withHardness(0.5F).withDisabledStats().withDisabledNeighborNotifyOnMetadataChange().withTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_PICKAXE);

    @Override
    public void onInitialize() {
        LOGGER.info("ExampleMod initialized.");
    }

	@Override
	public void beforeGameStart() {
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {


		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(Block.dirt)
			.create("toggledLampIdle_", new ItemStack(toggledLampIdle, 1, 10));

		for(int i = 0; i <= 15; i++)
		{
			RecipeBuilder.Shapeless(MOD_ID)
				.addInput(Block.leverCobbleStone)
				.addInput(Block.lampIdle, i)
				.create("toggledLampIdle_"+i, new ItemStack(toggledLampIdle, 1, i));
		}

	}
}
