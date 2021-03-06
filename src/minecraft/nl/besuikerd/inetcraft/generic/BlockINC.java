package nl.besuikerd.inetcraft.generic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import nl.besuikerd.inetcraft.core.BlockSide;
import nl.besuikerd.inetcraft.core.INCLogger;
import nl.besuikerd.inetcraft.core.NetworkCraftIconRegister;

public class BlockINC extends Block{
	
	public BlockINC(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	@Override
	public final void registerIcons(IconRegister reg) {
		registerIcons(new NetworkCraftIconRegister(reg));
	}
	
	public void registerIcons(NetworkCraftIconRegister reg){
	}
	
	public String appendUnlocalizedName(String toAppend){
		String newName = String.format("%s.%s", getUnlocalizedName(), toAppend);
		setUnlocalizedName(newName);
		return newName;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entity, ItemStack stack) {
		int side = -1;
		if (MathHelper.abs((float)entity.posX - (float)x) < 2.0F && MathHelper.abs((float)entity.posZ - (float)z) < 2.0F) {
            double d0 = entity.posY + 1.82D - (double)entity.yOffset;
            if (d0 - (double)y > 2.0D) {
                side = 1;
            }
            if ((double)y - d0 > 0.0D) {
                side = 0;
            }
        }
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int direction = l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
        side = side != -1 ? side : direction;
		onBlockPlacedPositioned(world, x, y, z, BlockSide.lookup(side), BlockSide.lookup(direction), stack);
	}
	
	public void onBlockPlacedPositioned(World world, int x, int y, int z, BlockSide side, BlockSide direction, ItemStack stack){
		INCLogger.debug("block placed with side: %s and direction: %s", side, direction);
	}
}
