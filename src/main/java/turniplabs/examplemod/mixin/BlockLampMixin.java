
package turniplabs.examplemod.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLamp;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BlockLamp.class, remap = false)
public class BlockLampMixin {
/*
	@Shadow
	private boolean isActive;

	public BlockLampMixin(String key, int id, boolean isActivated) {
		super(key, id, isActivated);
	}


	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		player.addChatMessage("tEST");
		isActive = true;
		world.setBlockAndMetadataWithNotify(x, y, z, Block.lampActive.id, world.getBlockMetadata(x, y, z));
	}*/
}

