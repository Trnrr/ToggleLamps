package trnrr.toggleablelamps;

import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class ToggleLampBlock extends Block {

	private static final int[] texCoordsInactive = new int[16];
	private static final int[] texCoordsActive = new int[16];
	boolean isActive;

	public ToggleLampBlock(String key, int id, boolean isActivated) {
		super(key, id, Material.stone);
		this.isActive = isActivated; //NEED
	}
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if (world.getBlockId(x,y,z) == 8888)
		{
			world.setBlockAndMetadataWithNotify(x,y,z,8889,world.getBlockMetadata(x,y,z));
			return true;
		}
		else if (world.getBlockId(x,y,z) == 8889)
		{
			world.setBlockAndMetadataWithNotify(x,y,z,8888,world.getBlockMetadata(x,y,z));
			return true;
		}
		return false;
	}

	/*@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		player.addChatMessage("Test");
		player.addChatMessage(String.valueOf(world.getBlockId(x,y,z)));
		player.addChatMessage(String.valueOf(world.getBlockMetadata(x,y,z)));
		//world.getBlockMetadata(x,y,z);

		if (world.getBlockId(x,y,z) == 8888)
		{
			player.addChatMessage("==8888: " + world.getBlockId(x,y,z));
			world.setBlockAndMetadataWithNotify(x,y,z,8889,world.getBlockMetadata(x,y,z));
		}
		else if (world.getBlockId(x,y,z) == 8889)
		{
			player.addChatMessage("==8889: " + world.getBlockId(x,y,z));
			world.setBlockAndMetadataWithNotify(x,y,z,8888,world.getBlockMetadata(x,y,z));
		}
	}*/

	public int getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
		int data = blockAccess.getBlockMetadata(x, y, z);
		return this.isActive ? texCoordsActive[data & 15] : texCoordsInactive[data & 15];
	}

	public int getBlockTextureFromSideAndMetadata(Side side, int data) {
		return texCoordsActive[data & 15];
	}

	public static int getMetadataForColour(int i) {
		return ~i & 15;
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(ToggleableLamps.toggledLampIdle, 1, meta)};
	}

	static {
		texCoordsInactive[0] = Block.texCoordToIndex(17, 31);

		int i;
		for(i = 1; i < 16; ++i) {
			texCoordsInactive[i] = texCoordsInactive[0] - i % 8 * Global.TEXTURE_ATLAS_WIDTH_TILES - i / 8;
		}

		texCoordsActive[0] = Block.texCoordToIndex(19, 31);

		for(i = 1; i < 16; ++i) {
			texCoordsActive[i] = texCoordsActive[0] - i % 8 * Global.TEXTURE_ATLAS_WIDTH_TILES - i / 8;
		}

	}

}
